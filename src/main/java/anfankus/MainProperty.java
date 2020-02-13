package anfankus;

import org.springframework.beans.factory.annotation.Value;

public class MainProperty {

  @Value("${front_side.host}")
  static String host;
  @Value("${front_side.port}")
  static String port;

  public static String getAddress() {
    return "http://" + host + ":" + port;
  }
}
