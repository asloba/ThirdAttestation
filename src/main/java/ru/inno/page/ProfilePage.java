package ru.inno.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.inno.page.block.RowsCount;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {
    public ProfilePage() {
    }

    By tableRowCounts = By.cssSelector("[aria-label='rows per page']");
    By noRowsFoundLabel = By.cssSelector(".rt-noData");
    List<SelenideElement> bookInfoInProfile = $$(By.cssSelector("span.mr-2"));
    By deleteAllBooksButton = By.cssSelector("div.di button");
    By confirmDeletionBooksButton = By.id("closeSmallModal-ok");

    public void chooseRowsCount(RowsCount rowsCount) {
        $(tableRowCounts).scrollTo().selectOptionByValue(rowsCount.getName());
    }

    public void checkNoRowsLabelIsVisible() {
        $(noRowsFoundLabel).shouldBe(Condition.visible);
    }

    public ArrayList<SelenideElement> getBookListInProfile(){
        return new ArrayList<>(bookInfoInProfile);
    }

    public void deleteAllBooks() {
        $(deleteAllBooksButton).scrollTo().click();
        $(confirmDeletionBooksButton).should(Condition.exist).click();
        Selenide.confirm();
    }
}
