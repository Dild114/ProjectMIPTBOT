package app.api.repository;

import app.api.entity.Category;
import app.api.entity.CategoryId;
import app.api.entity.UserId;

import java.util.List;

public interface CategoryRepository {
  CategoryId getCategoryId();

  List<Category> findAll(UserId userId);

  Category findById(CategoryId id);

  void delete(CategoryId id, UserId userId);

  boolean create(Category category);
}
