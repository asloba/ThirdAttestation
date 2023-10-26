package ru.inno.block;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Header {

    public Header() {
    }

    static By usernameLabel = By.id("userName-value");

    public static void checkUserName(String username) {
        $(usernameLabel).shouldHave(Condition.text(username));
    }

    public static void awaitUserNameLabelAppears() {
        $(usernameLabel).shouldBe(Condition.visible);
    }
}
