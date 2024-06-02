package user;

import lombok.Getter;
import lombok.Setter;

public class CreatedUser {
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;

    public CreatedUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

}