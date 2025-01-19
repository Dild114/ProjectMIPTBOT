package app.api.service.exception;

public class UserCreateException extends RuntimeException {
  public UserCreateException(String message) {
    super(message);
  }
  public UserCreateException(String message, Throwable cause) {
    super(message, cause);
  }
}
