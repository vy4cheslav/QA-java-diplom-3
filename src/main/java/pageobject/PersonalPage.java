package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static config.Constants.LOGIN_URI;

public class PersonalPage {
    private final WebDriver webDriver;
    private By userMenu = By.className("Account_nav__Lgali");
    private By exitButton = By.xpath("//button[contains(text(),'Выход')]");
    private By constructor = By.xpath(".//p[text()='Конструктор']");

    public PersonalPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Проверка отображения меню пользователя внутри личного кабинета")
    public boolean checkPersonalMenuIsDisplayed() {
        return webDriver.findElement(userMenu).isDisplayed();
    }

    @Step("Клик по кнопке 'Выход'")
    public void clickExitButton() {
        webDriver.findElement(exitButton).click();
    }

    @Step("Открытие страницы личного кабинета")
    public void open() {
        webDriver.get(LOGIN_URI);
    }

    @Step("Клик по кнопке 'Конструктор' ввеху страницы")
    public void clickConstructorButton() {
        webDriver.findElement(constructor).click();
    }

}