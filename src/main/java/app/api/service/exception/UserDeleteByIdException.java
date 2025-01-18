package app.api.service.exception;

public class UserDeleteByIdException extends RuntimeException {
  public UserDeleteByIdException(String message) {
    super(message);
  }
  public UserDeleteByIdException(String message, Throwable cause) {
    super(message, cause);
  }
}
