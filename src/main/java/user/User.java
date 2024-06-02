package user;

import lombok.Getter;
import lombok.Setter;

public class User extends NewUser {
    @Setter @Getter
    private String email;
    @Setter @Getter
    private String password;
    @Setter @Getter
    private String name;

    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }
}

