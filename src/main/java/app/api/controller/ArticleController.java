package app.api.controller;

import app.api.controller.request.ArticleRequest;
import app.api.entity.Article;
import app.api.entity.UserId;
import app.api.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.List;

public class ArticleController implements Controller {
  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
  private final Service service;
  private final ArticleService articleService;
  private final ObjectMapper objectMapper;

  public ArticleController(Service service, ArticleService articleService, ObjectMapper objectMapper) {
    this.service = service;
    this.articleService = articleService;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    getArticle();
  }

  private void getArticle() {
    service.get("/articles",
      (Request request, Response response) -> {
      String body = request.body();
      ArticleRequest articleRequest = objectMapper.readValue(body, ArticleRequest.class);
      try {
        // тут тоже нужен userId сделано
        List<Article> articles = articleService.getArticles(new UserId(articleRequest.userId()));
        response.status(200);
        return objectMapper.writeValueAsString(articles);
      } catch (Exception e) {
        response.status(500);
        if (LOG.isErrorEnabled()) {
          LOG.error("Error when getting articles");
        }
        return objectMapper.writeValueAsString("Error when getting articles");
      }
    });
  }
}