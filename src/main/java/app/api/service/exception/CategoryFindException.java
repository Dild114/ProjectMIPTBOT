package app.api.service.exception;

public class CategoryFindException extends RuntimeException {
  public CategoryFindException(String message, Throwable cause) {
    super(message, cause);
  }
}
