package user;

import com.github.javafaker.Faker;

public class Generator {
    static Faker faker = new Faker();
    public static NewUser randomUser(){
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(6,12);
        final String name = faker.name().firstName();
        return new User(email,password,name);
    }
    public static NewUser userWithPasswordFiveSymbols(){
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(4,5);
        final String name = faker.name().firstName();
        return new User(email,password,name);
    }
}