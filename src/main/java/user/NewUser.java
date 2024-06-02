package user;

import lombok.Getter;
import lombok.Setter;

public class NewUser {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String name;

    public NewUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public NewUser() {
    }
}

