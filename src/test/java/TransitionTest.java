import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.AuthPage;
import pageobject.MainPage;
import pageobject.PersonalPage;
import user.Generator;
import user.NewUser;
import user.UserClient;

public class TransitionTest extends BaseTest {
    MainPage mainPage;
    PersonalPage personalPage;
    AuthPage authPage;
    private String userToken;
    UserClient userClient = new UserClient();
    private NewUser randomUser = Generator.randomUser();

    @Before
    public void setup() {
        mainPage = new MainPage(webDriver);
        personalPage = new PersonalPage(webDriver);
        authPage = new AuthPage(webDriver);
        userClient.creatingUser(randomUser);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void transitionToPersonalPage() {
        mainPage.open();
        mainPage.clickPersonalInfoButton();
        authPage.authUser(randomUser.getEmail(), randomUser.getPassword());
        mainPage.clickPersonalInfoButton();
        Assert.assertEquals(true, personalPage.checkPersonalMenuIsDisplayed());
        personalPage.clickExitButton();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transitionFromPersonalPageToConstructor() {
        personalPage.open();
        personalPage.clickConstructorButton();
        Assert.assertEquals(true, mainPage.checkConstructorMenuIsVisible());

    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void exit() {
        mainPage.open();
        mainPage.clickPersonalInfoButton();
        authPage.authUser(randomUser.getEmail(), randomUser.getPassword());
        mainPage.clickPersonalInfoButton();
        personalPage.clickExitButton();
        Assert.assertEquals(true, authPage.checkLoginButtonIsVisible());
    }

    @Test
    @DisplayName("Проверка переключателя в конструкторе бургера")
    public void checkConstructorTransitions() {
        mainPage.open();
        mainPage.switchToSauses();
        mainPage.checkSausesIsVisible();
        mainPage.switchToBuns();
        mainPage.checkBunsIsVisible();
        mainPage.switchToFilling();
        mainPage.checkFillingsIsVisible();
    }
    @After
    public void clean(){
        ValidatableResponse validatableResponse = userClient.authUser(randomUser);
        userToken = validatableResponse.extract().path("accessToken");
        userClient.deleteUser(userToken);
        webDriver.quit();
    }
}
