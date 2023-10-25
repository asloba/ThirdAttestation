package ru.inno.page.block;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Header {

    By usernameLabel = By.id("#userName-value");

    public void checkUserName(String username) {
        $(usernameLabel).shouldHave(Condition.text(username));
    }

    public void awaitUserNameLabelAppears() {
        $(usernameLabel).shouldBe(Condition.visible);
    }
}
