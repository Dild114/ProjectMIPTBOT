package app.api.controller;

import app.api.controller.request.CategoryRequest;
import app.api.entity.Category;
import app.api.entity.CategoryId;
import app.api.entity.UserId;
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
    getMyCategory();
    addCategory();
    deleteCategory();
  }

  private void getMyCategory() {
    service.get("/categories",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          CategoryRequest categoryRequest = objectMapper.readValue(body, CategoryRequest.class);
          try {
            // нужна проверка по userId и изменить название на myCategories сделано
            List<Category> categories = categoryService.findAll(new UserId(categoryRequest.userId()));
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
          CategoryRequest categoryRequest = objectMapper.readValue(body, CategoryRequest.class);

          int userid = categoryRequest.userId();
          String nameCategory = categoryRequest.nameCategory();
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
      String body = request.body();
      CategoryRequest categoryRequest = objectMapper.readValue(body, CategoryRequest.class);

        try {
        categoryService.delete(new CategoryId(id), new UserId(categoryRequest.userId()));
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
