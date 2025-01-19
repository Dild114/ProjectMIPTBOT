package app.api.service.exception;

public class CategoryDuplicateException extends RuntimeException {
  public CategoryDuplicateException(String message) {
    super(message);
  }
  public CategoryDuplicateException(String message, Throwable cause) {
    super(message, cause);
  }
}
