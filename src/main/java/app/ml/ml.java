package app.ml;

import ai.onnxruntime.*;
import ai.djl.huggingface.tokenizers.Encoding;
import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;
import ai.djl.util.PairList;

import java.io.IOException;
import java.nio.file.Paths;

public class ml {
  private static final Logger LOG = LoggerFactory.getLogger(ml.class);

  // Токенизация текста заранее сохранённым токенизатором
  private static Encoding[] tokenizer(String text, String[] groups) throws IOException {
    LOG.info("Начало токенизации");
    HuggingFaceTokenizer tokenizer = HuggingFaceTokenizer.newInstance(Paths.get("src/main/resources/ml/tokenizer"));

    PairList<String, String> pairList = new PairList<>();

    for (String group : groups) {
      pairList.add(text, group);
    }
    LOG.info("Токенизация завершена");
    return tokenizer.batchEncode(pairList, true, true);
  }

  // Метод для использования самой модели
  private static Float[] model(Encoding[] encodedTokens) throws OrtException {
    // Создаём окружение для нашей модели и запускаем сессию
    LOG.info("Начало работы модели");
    OrtEnvironment  env = OrtEnvironment.getEnvironment();
    var session = env.createSession("src/main/resources/ml/model.onnx", new OrtSession.SessionOptions());

    int tokensAndMasksLength = encodedTokens[0].getIds().length;

    // Создаём массивы токенов и масок внимания и заполняем их токенизированными входными данными
    long[][] tokenArray = new long[encodedTokens.length][tokensAndMasksLength];
    long[][] attentionMaskArray = new long[encodedTokens.length][tokensAndMasksLength];

    for (int i = 0; i < encodedTokens.length; i++) {
      long[] tokenIds = encodedTokens[i].getIds();
      long[] attentionMask = encodedTokens[i].getAttentionMask();

      System.arraycopy(tokenIds, 0, tokenArray[i], 0, tokensAndMasksLength);
      System.arraycopy(attentionMask, 0, attentionMaskArray[i], 0, tokensAndMasksLength);
    }

    // Создаём тензоры для токенов и масок, чтобы передать их модели
    OnnxTensor tensorForTokens = OnnxTensor.createTensor(env, tokenArray);
    OnnxTensor tensorForAttentionMask = OnnxTensor.createTensor(env, attentionMaskArray);

    Map<String, OnnxTensor> input = new HashMap<>();
    input.put("input_ids", tensorForTokens);
    input.put("attention_mask", tensorForAttentionMask);

    // Передаём модели входные данные и получаем результат её работы
    OrtSession.Result outputs = session.run(input);

    // Переводим результат работы в тензор для дальнейшей работы с ним
    OnnxTensor tensor = (OnnxTensor) outputs.get(0);

    // Получаем сырые логиты как результат работы модели. Формат логитов: [следование, противоречие, нейтральность]
    float[][] logits = (float[][]) tensor.getValue();

    // Преобразуем сырые логиты в вероятности
    float[][] probabilities = softmax(logits);

    // Для каждой категории выбираем вероятность того, что она соответствует тексту
    Float[] entailmentProbabilities = new Float[probabilities.length];
    for (int i = 0; i < probabilities.length; i++) {
      entailmentProbabilities[i] = probabilities[i][0]; // Индекс 0 — Entailment
    }
    LOG.info("Модель завершила работу");
    return entailmentProbabilities;

  }

  //Математическая функция, позволяющая преобразовать сырые логиты в вероятности
  private static float[][] softmax(float[][] logits) {
    LOG.info("Начало вычисления softmax");
    float[][] probabilities = new float[logits.length][logits[0].length];
    for (int i = 0; i < logits.length; i++) {
      float maxLogit = logits[i][0];
      for (float value : logits[i]) {
        if (value > maxLogit) {
          maxLogit = value;
        }
      }

      float sum = 0.0f;
      for (int j = 0; j < logits[i].length; j++) {
        probabilities[i][j] = (float) Math.exp(logits[i][j] - maxLogit);
        sum += probabilities[i][j];
      }

      for (int j = 0; j < logits[i].length; j++) {
        probabilities[i][j] /= sum;
      }
    }
    LOG.info("Вычисление softmax завершено");
    return probabilities;
  }

  // Метод, позволяющий создать Мапу из двух списков
  private static <T, V> Map<T, V> createMapFromArrays(T[] keys, V[] values) {
    LOG.info("Создание мапы из массивов");
    Map<T, V> map = new HashMap<>();
    for (int i = 0; i < keys.length; i++) {
      map.put(keys[i], values[i]);
    }
    LOG.info("Мапа создана. Количество элементов: {}", map.size());
    return map;
  }

  // Метод для нахождения вероятностей соответствия каждой категории тексту
  public static Map<String, Float> findProbabilitiesForCategories(String text, String[] candidateLabels) throws IOException, OrtException {
    LOG.info("Начало обработки текста");
    Encoding[] encodedTokens = tokenizer(text, candidateLabels);
    Float[] entailmentProbabilitiesForCandidates = model(encodedTokens);
    Map<String, Float> probabilitiesForCategories = createMapFromArrays(candidateLabels, entailmentProbabilitiesForCandidates);
    LOG.info("Обработка текста завершена. Результат: {}", probabilitiesForCategories);
    return probabilitiesForCategories;
  }

  public static void main(String[] args) throws IOException, OrtException {
    String text = "Как настроить CI/CD для проекта на GitLab?";
    String[] candidateLabels = {"DevOps", "IT", "Frontend", "Backend", "Data Science", "Machine Learning", "Cybersecurity", "Cloud Computing", "Mobile Development", "Game Development", "Database Administration"};

    Encoding[] encodedTokens = tokenizer(text, candidateLabels);
    Float[] entailmentProbabilitiesForCandidates = model(encodedTokens);
    Map<String, Float> probabilitiesForCategories = createMapFromArrays(candidateLabels, entailmentProbabilitiesForCandidates);
    System.out.println(probabilitiesForCategories);
  }
}
