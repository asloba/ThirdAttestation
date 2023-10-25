package ru.inno.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

public class Configurator {
    public static void setConfiguration(){
        Configuration.pageLoadTimeout = 90000;
        Configuration.browserSize = "1920x1070";
    }

    public static void openSite(String url){
        setConfiguration();
        Selenide.open(url);
    }
}
