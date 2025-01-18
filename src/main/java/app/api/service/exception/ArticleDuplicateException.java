package app.api.service.exception;

public class ArticleDuplicateException extends RuntimeException {
  public ArticleDuplicateException(String message) {
    super(message);
  }
  public ArticleDuplicateException(String message, Throwable cause) {
    super(message, cause);
  }
}
