package app.api.service.exception;

public class ArticleDeleteByIdException extends RuntimeException {
  public ArticleDeleteByIdException(String message) {
    super(message);
  }
  public ArticleDeleteByIdException(String message, Throwable cause) {
    super(message, cause);
  }
}
