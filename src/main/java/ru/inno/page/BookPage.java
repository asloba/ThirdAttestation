package ru.inno.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class BookPage {

    public BookPage() {
    }

    By addToCollection = By.xpath("//button[text()='Add To Your Collection']");
    By backToStore = By.xpath("//button[text()='Back To Book Store']");

    public BooksPage addBookToMyCollectionAndBackToStore() {
        $(addToCollection).scrollTo().shouldHave(Condition.appear, Duration.ofSeconds(45)).click();
        Selenide.confirm();
        $(backToStore).click();
        return new BooksPage();
    }
}
