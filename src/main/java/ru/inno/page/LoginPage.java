package ru.inno.page;

import org.openqa.selenium.By;
import ru.inno.utils.TestProperties;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends Page{

    By userNameInput = By.id("userName");
    By passwordInput = By.id("password");
    By loginButton = By.id("login");

    String userNameValue = TestProperties.getProperties().getUserName();
    String passwordValue = TestProperties.getProperties().getPassword();

    public void auth() {
        $(userNameInput).sendKeys(userNameValue);
        $(passwordInput).sendKeys(passwordValue);
        $(loginButton).click();
        header.checkUserName(userNameValue);
    }
}
