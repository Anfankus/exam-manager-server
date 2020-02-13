package anfankus.dao;

import lombok.Data;

@Data
public class User {

  private String userid;
  private String username;
  private String password;
  private Integer usertype;
}
