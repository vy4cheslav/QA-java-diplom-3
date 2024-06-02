package user;


import config.Client;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static config.Constants.*;
import static io.restassured.RestAssured.given;

public class UserClient {
    CreatedUser createdUser;
    @Step("Авторизация пользователя")
    public ValidatableResponse authUser(NewUser newUser) {
        createdUser = new CreatedUser(newUser.getEmail(), newUser.getPassword());
        return given()
                .spec(Client.spec())
                .body(createdUser)
                .when()
                .post(LOG_IN)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(Client.spec())
                .headers("Authorization", accessToken)
                .when()
                .delete(AUTH_USER)
                .then();
    }

    @Step("Создание пользователя")
    public ValidatableResponse creatingUser(NewUser newUser) {
        return given()
                .spec(Client.spec())
                .body(newUser)
                .when()
                .post(CREATE_USER)
                .then();
    }
}
