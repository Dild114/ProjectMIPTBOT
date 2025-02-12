package app.api.repository;

import app.api.entity.Category;
import app.api.entity.CategoryId;
import app.api.entity.UserId;
import app.api.repository.exception.dbDuplicateException;
import app.api.repository.exception.dbNotFoundException;

import java.util.List;

public class dbCategoryRepository implements CategoryRepository {
  dbRepository db;
  public dbCategoryRepository(dbRepository db) {
    this.db = db;
  }

  @Override
  public CategoryId getCategoryId() {
    return db.generateIdCategory();
  }

  @Override
  public List<Category> findAll(UserId userId) {
    return db.findAllCategory(userId);
  }

  @Override
  public Category findById(CategoryId id) {
    Category category = db.findCategoryById(id);
    if (category == null) {
      throw new dbNotFoundException("Category not found");
    } else {
      return category;
    }
  }

  @Override
  public void delete(CategoryId id, UserId userId) {
    boolean flag = db.deleteCategory(id, userId);
    if (!flag) {
      throw new dbNotFoundException("Category not found");
    }
  }

  @Override
  public boolean create(Category category) {
    boolean flag = db.addCategory(category);
    if (!flag) {
      throw new dbDuplicateException("Category not found");
    }
    return flag;
  }
}
