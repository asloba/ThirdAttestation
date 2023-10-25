import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.inno.page.BookPage;
import ru.inno.page.BooksPage;
import ru.inno.page.ProfilePage;
import ru.inno.page.block.RowsCount;

import static com.codeborne.selenide.Selenide.$;
import static ru.inno.utils.Configurator.openSite;

public class DemoQATest {

    String loginUrl = "https://demoqa.com/login";

    @BeforeEach
    public void openPage(){
        openSite(loginUrl);
    }

    @Test()
    @DisplayName("Проверка авторизации и открытия пустого профиля")
    public void checkAuthorizationAndOpenProfile(){
        ProfilePage profilePage = new ProfilePage();
        $("#userName").sendKeys("Alyona");
        $("#password").sendKeys("Qwerty432!");
        $("#login").click();
        $("#userName-value").shouldHave(Condition.text("Alyona"));
        Assertions.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://demoqa.com/profile");
        profilePage.checkNoRowsLabelIsVisible();
    }

    @Test
    @DisplayName("Проверка добавления 6 книг в профиль")
    public void checkAddingBooksToProfile() {
        BooksPage booksPage = new BooksPage();
        BookPage bookPage = new BookPage();
        ProfilePage profilePage = new ProfilePage();
        int booksCount = 6;
        $("#userName").sendKeys("Alyona");
        $("#password").sendKeys("Qwerty432!");
        $("#login").click();
        $("#userName-value").shouldHave(Condition.text("Alyona"));
        $(By.xpath("//span[text()='Book Store']/parent::li")).scrollTo().click();
        for (int i = 0; i < booksCount; i++) {
            booksPage.clickBooks(i);
            bookPage.addBookToMyCollectionAndBackToStore();
        }
        $(By.xpath("//span[text()='Profile']/parent::li")).scrollTo().click();
        profilePage.chooseRowsCount(RowsCount.ROWS_10);
        Assertions.assertEquals(booksCount, profilePage.getBookListInProfile().size());
    }

    @Test
    @DisplayName("Проверка добавления книг в профиль и последующего удаления их из профиля")
    public void checkAddingBooksToProfileAndDeletingThem() {
        BooksPage booksPage = new BooksPage();
        BookPage bookPage = new BookPage();
        ProfilePage profilePage = new ProfilePage();
        int booksCount = 2;
        $("#userName").sendKeys("Alyona");
        $("#password").sendKeys("Qwerty432!");
        $("#login").click();
        $("#userName-value").shouldHave(Condition.text("Alyona"));
        $(By.xpath("//span[text()='Book Store']/parent::li")).scrollTo().click();
        for (int i = 0; i < booksCount; i++) {
            booksPage.clickBooks(i);
            bookPage.addBookToMyCollectionAndBackToStore();
        }
        $(By.xpath("//span[text()='Profile']/parent::li")).scrollTo().click();
        $("#userName-value").shouldHave(Condition.text("Alyona"));
//        profilePage.chooseRowsCount(RowsCount.ROWS_10);
        Assertions.assertEquals(booksCount, profilePage.getBookListInProfile().size());
        profilePage.deleteAllBooks();
        profilePage.checkNoRowsLabelIsVisible();
    }
}
