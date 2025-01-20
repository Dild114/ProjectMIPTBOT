package app.api.stub;

import app.ml.FindCategoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MlStubRepository implements FindCategoryRepository {
  List<String> categories;
  public MlStubRepository() {
    categories = new ArrayList<>();
    categories.add("Category1");
    categories.add("Category2");
    categories.add("Category3");
    categories.add("Category4");
  }
  @Override
  public String findCategory(String article, List<String> categories) {
    HashMap<String, Double> map = new HashMap<>();
    String answer = "";
    for (String category : categories) {
      map.put(category, category.length() % 12.5 / 10.0);
    }
    double max = -1;
    for (String key : map.keySet()) {
      if (map.get(key) > max) {
        max = map.get(key);
        answer = key;
      }
    }
    return answer;
  }
}
