package app.api.service.exception;

public class UserDuplicateException extends RuntimeException {
  public UserDuplicateException(String message) {
    super(message);
  }
  public UserDuplicateException(String message, Throwable cause) {
    super(message, cause);
  }
}
