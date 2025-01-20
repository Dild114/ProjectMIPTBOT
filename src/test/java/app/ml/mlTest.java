package app.ml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ai.onnxruntime.OrtException;

public class mlTest {
  @Test
  void findTwoMostProbableCategories() throws IOException, OrtException {
    String text = "Как настроить CI/CD для проекта на GitLab?";
    String[] candidateLabels = {"DevOps", "IT", "Frontend", "Backend", "Data Science", "Machine Learning", "Cybersecurity", "Cloud Computing", "Mobile Development", "Game Development", "Database Administration"};
    ml ml = new ml();
    Map<String, Float> probabilities = ml.findTwoMostProbableCategories(text, candidateLabels);
    
    Map<String, Float> mapForTwoCategoriesWithMaxProbabilities = new HashMap<>();
    mapForTwoCategoriesWithMaxProbabilities.put("Data Science", 0.039048787f);
    mapForTwoCategoriesWithMaxProbabilities.put("Backend", 0.048307437f);
    
    assertEquals(probabilities, mapForTwoCategoriesWithMaxProbabilities);
  }
}