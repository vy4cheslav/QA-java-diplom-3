import user.Generator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import pageobject.*;
import user.NewUser;
import user.UserClient;

public class LoginTest extends BaseTest{

    MainPage mainPage;
    AuthPage authPage;
    RegistrationPage registrationPage;
    PersonalPage personalPage;
    UserClient userClient = new UserClient();
    private String userToken;
    private NewUser randomNewUser = Generator.randomUser();

    @Before
    public void createUser(){
        mainPage = new MainPage(webDriver);
        authPage = new AuthPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        personalPage = new PersonalPage(webDriver);
        userClient.creatingUser(randomNewUser);
        mainPage.open();
    }
    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной")
    public void authByLoginButton(){
        mainPage.clickLoginButton();
        authPage.authUser(randomNewUser.getEmail(), randomNewUser.getPassword());
        mainPage.waiting();
        mainPage.clickPersonalInfoButton();
        Assert.assertEquals(personalPage.checkPersonalMenuIsDisplayed(), true);
        personalPage.clickExitButton();
    }
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void authByPersonalInfoButton(){
        mainPage.clickPersonalInfoButton();
        authPage.authUser(randomNewUser.getEmail(), randomNewUser.getPassword());
        mainPage.clickPersonalInfoButton();
        Assert.assertEquals(personalPage.checkPersonalMenuIsDisplayed(), true);
        personalPage.clickExitButton();
    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void authByReistrationFormButton(){
        mainPage.clickPersonalInfoButton();
        authPage.clickRegistrationButton();
        registrationPage.clickLoginButton();
        authPage.authUser(randomNewUser.getEmail(), randomNewUser.getPassword());
        mainPage.waiting();
        mainPage.clickPersonalInfoButton();
        Assert.assertEquals(personalPage.checkPersonalMenuIsDisplayed(), true);
        personalPage.clickExitButton();

    }
    @Test
    @DisplayName("Авторизация через кнопку восстановления пароля")
    public void authByPasswordRecoveryButton(){
        mainPage.clickPersonalInfoButton();
        authPage.clickPasswordRecoveryButton();
        authPage.clickAuthButtonFromRecoveryPassword();
        authPage.authUser(randomNewUser.getEmail(), randomNewUser.getPassword());
        mainPage.clickPersonalInfoButton();
        Assert.assertEquals(personalPage.checkPersonalMenuIsDisplayed(), true);
        personalPage.clickExitButton();
    }

    @After
    public void clean(){
        ValidatableResponse validatableResponse = userClient.authUser(randomNewUser);
        userToken = validatableResponse.extract().path("accessToken");
        userClient.deleteUser(userToken);
        webDriver.quit();
    }
}
