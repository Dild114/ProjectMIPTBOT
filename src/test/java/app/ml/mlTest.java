package app.ml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import ai.onnxruntime.OrtException;

public class mlTest {
  @Test
  void testFindProbabilitiesForCategories() throws IOException, OrtException {
    String text = "Как настроить CI/CD для проекта на GitLab?";
    String[] candidateLabels = {"DevOps", "IT", "Frontend", "Backend", "Data Science", "Machine Learning", "Cybersecurity", "Cloud Computing", "Mobile Development", "Game Development", "Database Administration"};

    Map<String, Float> probabilities = ml.findProbabilitiesForCategories(text, candidateLabels);
    
    Map<String, Float> probabilitiesForCategories = new HashMap<>();
    probabilitiesForCategories.put("Data Science", 0.039048802f);
    probabilitiesForCategories.put("DevOps", 0.011645611f);
    probabilitiesForCategories.put("Backend", 0.048307385f);
    probabilitiesForCategories.put("Database Administration", 0.031594772f);
    probabilitiesForCategories.put("Cloud Computing", 0.012672423f);
    probabilitiesForCategories.put("Frontend", 0.028294854f);
    probabilitiesForCategories.put("Mobile Development", 0.008227297f);
    probabilitiesForCategories.put("Cybersecurity", 0.015962604f);
    probabilitiesForCategories.put("IT", 0.03644597f);
    probabilitiesForCategories.put("Machine Learning", 0.012106622f);
    probabilitiesForCategories.put("Game Development", 0.017827472f);
    
    assertEquals(probabilities, probabilitiesForCategories);
  }
}
