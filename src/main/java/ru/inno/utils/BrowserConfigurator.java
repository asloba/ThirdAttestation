package ru.inno.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Configuration.baseUrl;

public class BrowserConfigurator {
    public static void setUpTests(){
        Configuration.pageLoadTimeout = 90000;
        baseUrl = TestProperties.getProperties().getBaseUrl();
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }

    public static void openSite(){
        setUpTests();
        Selenide.open(baseUrl + TestProperties.getProperties().getLoginEndpoint());
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}
