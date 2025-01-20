package app.ml;

import java.util.List;

public interface FindCategoryRepository {
  String findCategory(String article, List<String> categories);
}
