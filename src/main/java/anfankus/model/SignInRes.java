package anfankus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class SignInRes {

  private Boolean success;
  private Integer usertype;
  private String username;
  private String msg;
}
