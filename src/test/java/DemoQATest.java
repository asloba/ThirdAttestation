import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.inno.page.BookPage;
import ru.inno.page.BooksPage;

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
        $("#userName").sendKeys("Alyona");
        $("#password").sendKeys("Qwerty432!");
        $("#login").click();
        $("#userName-value").shouldHave(Condition.text("Alyona"));
        Assertions.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://demoqa.com/profile");
        $(".rt-noData").should(Condition.exist);
    }

    @Test
    @DisplayName("Проверка добавления 6 книг в мою коллекцию")
    public void checkAddingBooksToProfile() {
        BooksPage booksPage = new BooksPage();
        BookPage bookPage = new BookPage();
        $("#userName").sendKeys("Alyona");
        $("#password").sendKeys("Qwerty432!");
        $("#login").click();
        $("#userName-value").shouldHave(Condition.text("Alyona"));
        $(By.xpath("//span[text()='Book Store']/parent::li")).scrollTo().click();
        for (int i = 0; i < 6; i++) {
            booksPage.clickBooks(i);
            bookPage.addBookToMyCollectionAndBackToStore();
        }

    }
}
