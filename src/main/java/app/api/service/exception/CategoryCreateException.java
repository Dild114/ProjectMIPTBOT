package app.api.service.exception;

public class CategoryCreateException extends RuntimeException {
  public CategoryCreateException(String message) {
    super(message);
  }
  public CategoryCreateException(String message, Throwable cause) {
    super(message, cause);
  }
}
