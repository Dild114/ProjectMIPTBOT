package app.api.repository.exception;

public class dbNotFoundException extends RuntimeException {
  public dbNotFoundException(String message) {
    super(message);
  }
  public dbNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
