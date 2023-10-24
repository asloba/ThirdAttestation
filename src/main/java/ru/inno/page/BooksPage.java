package ru.inno.page;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;


public class BooksPage extends Page{
    public BooksPage() {
    }

    By clickBook = By.cssSelector(".mr-2 a");

    public BookPage clickBooks(int index) {
            $$(clickBook).get(index).scrollTo().click();
        return new BookPage();
    }
}
