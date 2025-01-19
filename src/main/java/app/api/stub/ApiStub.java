//package app.api.stub;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import spark.Request;
//import spark.Response;
//import spark.Spark;
//
//public class ApiStub {
//  private static final Logger LOG = LoggerFactory.getLogger(ApiStub.class);
//
//  public static void main(String[] args) {
//    Spark.port(4567);
//
//    Spark.get("/sites", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос GET на /sites");
//      }
//      response.status(501);
//      return "Not Implemented for /sites GET";
//    });
//
//    Spark.post("/sites", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос POST на /sites");
//      }
//      response.status(501);
//      return "Not Implemented for /sites POST";
//    });
//
//    Spark.delete("/sites", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос DELETE на /sites");
//      }
//      response.status(501);
//      return "Not Implemented for /sites DELETE";
//    });
//
//    Spark.get("/sites/:id", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос GET на /sites/{}", request.params(":id"));
//      }
//      response.status(501);
//      return "Not Implemented for /sites/" + request.params(":id") + " GET";
//    });
//
//    Spark.delete("/sites/:id", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос DELETE на /sites/{}", request.params(":id"));
//      }
//      response.status(501);
//      return "Not Implemented for /sites/" + request.params(":id") + " DELETE";
//    });
//
//    Spark.get("/categories", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос GET на /categories");
//      }
//      response.status(501);
//      return "Not Implemented for /categories GET";
//    });
//
//    Spark.post("/categories", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос POST на /categories");
//      }
//      response.status(501);
//      return "Not Implemented for /categories POST";
//    });
//
//    Spark.delete("/categories", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос DELETE на /categories");
//      }
//      response.status(501);
//      return "Not Implemented for /categories DELETE";
//    });
//
//    Spark.post("/categories/:name", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос POST на /categories/{}", request.params(":name"));
//      }
//      response.status(501);
//      return "Not Implemented for /categories/" + request.params(":name") + " POST";
//    });
//
//    Spark.get("/categories/:id", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос GET на /categories/{}", request.params(":id"));
//      }
//      response.status(501);
//      return "Not Implemented for /categories/" + request.params(":id") + " GET";
//    });
//
//    Spark.delete("/categories/:id", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос DELETE на /categories/{}", request.params(":id"));
//      }
//      response.status(501);
//      return "Not Implemented for /categories/" + request.params(":id") + " DELETE";
//    });
//
//    Spark.post("/signup", (Request request, Response response) -> {
//      if (LOG.isInfoEnabled()) {
//        LOG.info("Запрос POST на /signup");
//      }
//      response.status(501);
//      return "Not Implemented for /signup POST";
//    });
//  }
//}