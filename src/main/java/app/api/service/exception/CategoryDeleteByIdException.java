package app.api.service.exception;

public class CategoryDeleteByIdException extends RuntimeException {
  public CategoryDeleteByIdException(String message) {
    super(message);
  }
  public CategoryDeleteByIdException(String message, Throwable cause) {
    super(message, cause);
  }
}
