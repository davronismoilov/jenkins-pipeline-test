package task1.isdaha.payload;

import lombok.Data;

@Data
public class RegisterUserReq {
  private   String email;
  private String password;
  private String  phoneNumber;
}
