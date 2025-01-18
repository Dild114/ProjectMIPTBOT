package app;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Service;


public class Main {
  public static void main(String[] args) {
    Service service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();

  }
}
