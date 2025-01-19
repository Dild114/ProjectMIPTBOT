package app.api.repository.exception;

public class dbDuplicateException extends RuntimeException {
  public dbDuplicateException(String message) {
    super(message);
  }
  public dbDuplicateException(String message, Throwable cause) {
    super(message, cause);
  }
}
