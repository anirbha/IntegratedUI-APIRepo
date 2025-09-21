package api.pojo;

import lombok.Getter;
import lombok.Setter;

public class UserPojo {
    @Setter
    @Getter
    int id;
    @Setter
    @Getter
    String username;
    @Setter
    @Getter
    String firstName;
    @Setter
    @Getter
    String lastName;
    @Setter
    @Getter
    String email;
    @Setter
    @Getter
    String password;
    @Setter
    @Getter
    String phone;
    @Setter
    @Getter
    int userStatus;
}
