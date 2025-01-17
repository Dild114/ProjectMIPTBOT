package app.ml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ai.onnxruntime.OrtException;

public class mlTest {
  @Test
  @Disabled("Тест временно отключён")
  void testFindProbabilitiesForCategories() throws IOException, OrtException {
    String text = "Как настроить CI/CD для проекта на GitLab?";
    String[] candidateLabels = {"DevOps", "IT", "Frontend", "Backend", "Data Science", "Machine Learning", "Cybersecurity", "Cloud Computing", "Mobile Development", "Game Development", "Database Administration"};

    Map<String, Float> probabilities = ml.findProbabilitiesForCategories(text, candidateLabels);
    
    Map<String, Float> probabilitiesForCategories = new HashMap<>();
    probabilitiesForCategories.put("Data Science", 0.42180878f);
    probabilitiesForCategories.put("DevOps", 0.86746943f);
    probabilitiesForCategories.put("Backend", 0.81870013f);
    probabilitiesForCategories.put("Database Administration", 0.5658511f);
    probabilitiesForCategories.put("Cloud Computing", 0.83641315f);
    probabilitiesForCategories.put("Frontend", 0.8449678f);
    probabilitiesForCategories.put("Mobile Development", 0.91840273f);
    probabilitiesForCategories.put("Cybersecurity", 0.18779641f);
    probabilitiesForCategories.put("IT", 0.88502884f);
    probabilitiesForCategories.put("Machine Learning", 0.8351789f);
    probabilitiesForCategories.put("Game Development", 0.57367396f);
    
    assertEquals(probabilities, probabilitiesForCategories);
  }
}
