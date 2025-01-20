package app.ml;

import ai.onnxruntime.OrtException;

import java.io.IOException;
import java.util.Map;

public interface FindTwoMostProbableCategories {
  Map<String, Float> findTwoMostProbableCategories(String text, String... candidateLabels) throws IOException, OrtException;
}
