import user.Generator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import pageobject.*;
import user.NewUser;
import user.UserClient;

public class RegistrationTest extends BaseTest{

    MainPage mainPage;
    AuthPage authPage;
    RegistrationPage registerPage;
    PersonalPage personalPage;
    UserClient userClient = new UserClient();
    private String userToken;
    private boolean isAccountCreated = false;
    private NewUser randomNewUser = Generator.randomUser();
    private NewUser randomUserPasswordFiveSymbols = Generator.userWithPasswordFiveSymbols();

    @Before
    public void init(){
        mainPage = new MainPage(webDriver);
        authPage = new AuthPage(webDriver);
        registerPage = new RegistrationPage(webDriver);
        personalPage = new PersonalPage(webDriver);
    }

    @Test
    @DisplayName("Проверка регистрации с рандомными данными")
    public void registrationRandomUser() {
        mainPage.open();
        mainPage.clickAuthButton();
        authPage.clickRegistrationButton();
        registerPage.setRegInformation(randomNewUser);
        mainPage.waiting();
        authPage.authUser(randomNewUser.getEmail(), randomNewUser.getPassword());
        mainPage.clickPersonalInfoButton();
        Assert.assertEquals(true, personalPage.checkPersonalMenuIsDisplayed());
        isAccountCreated = true;
        personalPage.clickExitButton();
    }
    @Test
    @DisplayName("Попытка регистрации с паролем из 5 символов")
    public void tryToRegWithShortPassword(){
        mainPage.open();
        mainPage.clickAuthButton();
        authPage.clickRegistrationButton();
        registerPage.setRegInformation(randomUserPasswordFiveSymbols);
        Assert.assertEquals(true, registerPage.checkErrorMessage());
    }

    @After
    public void clean(){
        if(isAccountCreated){
            ValidatableResponse validatableResponse = userClient.authUser(randomNewUser);
            userToken = validatableResponse.extract().path("accessToken");
            userClient.deleteUser(userToken);}
        webDriver.quit();
    }
}