package app.api.controller;

import app.api.controller.request.UserCreateRequest;
import app.api.entity.UserId;
import app.api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

public class UserController implements Controller {
  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
  private final Service service;
  private final UserService userService;
  private final ObjectMapper objectMapper;

  public UserController(Service service, UserService userService , ObjectMapper objectMapper) {
    this.service = service;
    this.userService = userService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    createUser();
    deleteUser();
  }

  private void createUser() {
    service.post("/signup",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          UserCreateRequest userCreateRequest = objectMapper.readValue(body, UserCreateRequest.class);
          try {
            UserId id = userService.createUser(userCreateRequest.name(), userCreateRequest.password());
            LOG.debug("user created successfully");
            response.status(201);
            return objectMapper.writeValueAsString("OK your id: " + id);
          } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
              LOG.error("UserCreateException");
            }
            response.status(400);
            return objectMapper.writeValueAsString("ERROR");
          }
        });
  }
  private void deleteUser() {
    service.delete("/signup/:id",
        (Request request, Response response) -> {
        response.type("application/json");
        int userId = Integer.parseInt(request.params("id"));
        try {
          userService.deleteUser(new UserId(userId));
          response.status(204);
          return objectMapper.writeValueAsString("delete with id " + userId);
        } catch (Exception e) {
          if (LOG.isErrorEnabled()) {
            LOG.error("UserDeleteException");
          }
        response.status(404);
        return objectMapper.writeValueAsString("ERROR NOT FOUND");}
        });
  }
}
