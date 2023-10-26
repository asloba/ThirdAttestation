package ru.inno.block;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static ru.inno.block.Header.awaitUserNameLabelAppears;

public class SideMenu {

    public SideMenu() {
    }

    public static By bookStorePageButton = By.xpath("//span[text()='Book Store']/parent::li");
    public static By profilePageButton = By.xpath("//span[text()='Profile']/parent::li");

    public static void goToPage(By locator) {
        $(locator).scrollTo().click();
        awaitUserNameLabelAppears();
    }
}
