package app.api.stub;

import app.ml.FindCategoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class mlStubRepository implements FindCategoryRepository {
  List<String> categories;
  public mlStubRepository() {
    categories = new ArrayList<>();
    categories.add("Category1");
    categories.add("Category2");
    categories.add("Category3");
    categories.add("Category4");
  }
  @Override
  public String findCategory(String article) {
    HashMap<String, Double> map = new HashMap<>();
    String answer = "";
    map.put("Category1", article.length() % 10.0);
    map.put("Category2", article.length() % 12.9);
    map.put("Category3", article.length() % 23.1);
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
