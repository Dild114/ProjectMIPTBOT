//package app.api.stub;
//
//import app.ml.FindTwoMostProbableCategories;
//
//import java.util.HashMap;
//
//public class MlStubRepository implements FindTwoMostProbableCategories {
//  @Override
//  public String findTwoMostProbableCategories(String article, String... candidateLabels) {
//    HashMap<String, Double> map = new HashMap<>();
//    String answer = "";
//    for (String category : candidateLabels) {
//      map.put(category, article.length() % 12.5 / 10.0);
//    }
//    double max = -1;
//    for (String key : map.keySet()) {
//      if (map.get(key) > max) {
//        max = map.get(key);
//        answer = key;
//      }
//    }
//    if (max >= 0.8) {
//      return answer;
//    }
//    return null;
//  }
//}
