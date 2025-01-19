package app.api.controller;

import app.api.controller.request.CategoryAddRequest;
import app.api.controller.request.SiteSetRequest;
import app.api.entity.Category;
import app.api.entity.CategoryId;
import app.api.entity.UserId;
import app.api.service.ArticleService;
import app.api.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.List;

public class CategoryController implements Controller {
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
    getAllCategory();
    addCategory();
    deleteCategory();
  }

  private void getAllCategory() {
    service.get("/categories",
        (Request request, Response response) -> {
          response.type("application/json");
          try {
            List<Category> categories = categoryService.findAll();
            String json = objectMapper.writeValueAsString(categories);
            response.status(200);
            return json;
          } catch (Exception e) {
          LOG.error("failed to get all categories", e);
          response.status(500);
          return objectMapper.writeValueAsString("error");
          }
        });
  }

  private void addCategory() {
    service.post("/category",
        (Request request, Response response) -> {
        response.type("application/json");
          String body = request.body();
          CategoryAddRequest categoryAddRequest = objectMapper.readValue(body, CategoryAddRequest.class);

          int userid = categoryAddRequest.userId();
          String nameCategory = categoryAddRequest.nameCategory();
          try {
            CategoryId id = categoryService.create(nameCategory, new UserId(userid));
            response.status(201);
            return objectMapper.writeValueAsString("OK id=" + id);
          } catch (Exception e) {
          LOG.error("failed to create category", e);
          response.status(500);
          return objectMapper.writeValueAsString("error");}
        });
  }

  private void deleteCategory() {
    service.post("/category/:id",
      (Request request, Response response) -> {
      response.type("application/json");
      int id = Integer.parseInt(request.params(":id"));
      try {
        categoryService.delete(new CategoryId(id));
        response.status(204);
        return objectMapper.writeValueAsString("OK");
      } catch (Exception e) {
        LOG.error("failed to delete category", e);
        response.status(500);
        return objectMapper.writeValueAsString("error");
      }
        });
  }
}
