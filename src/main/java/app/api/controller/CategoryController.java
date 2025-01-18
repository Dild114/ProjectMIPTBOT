package app.api.controller;

import app.api.service.ArticleService;
import app.api.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;

public class CategoryController {
  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
  private final Service service;
  private final CategoryService categoryService;
  private final ObjectMapper objectMapper;

  public CategoryController(Service service, CategoryService categoryService, ObjectMapper objectMapper) {
    this.service = service;
    this.categoryService = categoryService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
  }
}
