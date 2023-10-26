package ru.inno.page;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import ru.inno.block.Header;
import ru.inno.block.SideMenu;

public class Page {

    protected Header header;
    protected SideMenu sideMenu;

    public Page() {
        header = new Header();
        sideMenu = new SideMenu();
    }

    public String getCurrentUrl(){
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    public void checkCurrentUrl(String url) {
         Assertions.assertEquals(url, getCurrentUrl());
    }
}
