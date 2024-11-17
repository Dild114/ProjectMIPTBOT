package app.api.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Spark;

public class api {
  private static final Logger LOG = LoggerFactory.getLogger(api.class);

  public static void main(String[] args) {
    ObjectMapper objectMapper = new ObjectMapper();

    // Устанавливаем порт (по желанию)
    Spark.port(4567);

    Spark.get("/sites", (Request request, Response response) -> {
      LOG.info("запрос get на /sites");
      response.status(501);
      return null;
    });

    Spark.post("/sites", (Request request, Response response) -> {
      LOG.info("запрос post на /sites");
      response.status(501);
      return null;
    });

    Spark.delete("/sites", (Request request, Response response) -> {
      LOG.info("запрос delete на /sites");
      response.status(501);
      return null;
    });

    Spark.get("/sites/{id}", (Request request, Response response) -> {
      LOG.info("запрос get на /sites{id}");
      response.status(501);
      return null;
    });

    Spark.delete("/sites/{id}", (Request request, Response response) -> {
      LOG.info("запрос delete на /sites/{id}");
      response.status(501);
      return null;
    });

    Spark.get("/sites", (Request request, Response response) -> {
      LOG.info("запрос get на /sites");
      response.status(501);
      return null;
    });

    Spark.get("/categories", (Request request, Response response) -> {
      LOG.info("запрос get на /categories");
      response.status(501);
      return null;
    });

    Spark.post("/categories", (Request request, Response response) -> {
      LOG.info("запрос post на /categories");
      response.status(501);
      return null;
    });

    Spark.delete("/categories", (Request request, Response response) -> {
      LOG.info("запрос delete на /categories");
      response.status(501);
      return null;
    });

    Spark.post("/categories/{name}", (Request request, Response response) -> {
      LOG.info("запрос post на /categories{name}");
      response.status(501);
      return null;
    });

    Spark.get("/categories/{id}", (Request request, Response response) -> {
      LOG.info("запрос get на /categories/{id}");
      response.status(501);
      return null;
    });

    Spark.delete("/categories/{id}", (Request request, Response response) -> {
      LOG.info("запрос delete на /categories/{id}");
      response.status(501);
      return null;
    });

    Spark.post("/signup", (Request request, Response response) -> {
      LOG.info("запрос post на /signup");
      response.status(501);
      return null;
    });


  }
}