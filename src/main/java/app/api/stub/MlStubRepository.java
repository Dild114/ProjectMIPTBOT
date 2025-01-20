package app.api.stub;

import app.ml.FindCategoryRepository;

import java.util.HashMap;
import java.util.List;

public class MlStubRepository implements FindCategoryRepository {
  @Override
  public String findCategory(String article, List<String> categories) {
    HashMap<String, Double> map = new HashMap<>();
    String answer = "";
    for (String category : categories) {
      map.put(category, article.length() % 12.5 / 10.0);
    }
    double max = -1;
    for (String key : map.keySet()) {
      if (map.get(key) > max) {
        max = map.get(key);
        answer = key;
      }
    }
    if (max >= 0.8) {
      return answer;
    }
    return null;
  }
}
