package app.api.service;

import app.api.entity.Category;
import app.api.entity.CategoryId;
import app.api.repository.CategoryRepository;
import app.api.service.exception.*;

import java.util.List;

public class CategoryService {
  private final CategoryRepository categoryRepository;
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }
  public Category findById(CategoryId id)  throws CategoryFindException {
    try {
      return categoryRepository.findById(id);
    }   catch (CategoryNotFoundException e) {
      throw new CategoryFindException("Article with id " + id + " not found", e);
    }
  }
  public void delete(CategoryId id)  throws CategoryNotFoundException {
    try {
      categoryRepository.delete(id);
    } catch (CategoryNotFoundException e) {
      throw new CategoryNotFoundException("Category with id " + id + " not found", e);
    }
  }

  public CategoryId create(String name) {
    CategoryId categoryId = categoryRepository.getCategoryId();
    Category category = new Category(categoryId, name, null);
    try {
      categoryRepository.create(category);
    } catch (CategoryDuplicateException e) {
      throw new CategoryCreateException("Category with id " + category.id() + " already exists", e);
    }
    return categoryId;
  }

}
