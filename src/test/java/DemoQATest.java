import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import ru.inno.block.RowsCount;
import ru.inno.page.BookPage;
import ru.inno.page.BooksPage;
import ru.inno.page.LoginPage;
import ru.inno.page.ProfilePage;
import ru.inno.utils.ApiService;

import static io.qameta.allure.Allure.step;
import static ru.inno.block.Header.awaitUserNameLabelAppears;
import static ru.inno.block.SideMenu.*;
import static ru.inno.utils.BrowserConfigurator.openSite;
import static ru.inno.utils.BrowserConfigurator.setUpTests;

public class DemoQATest {

    @BeforeAll
    public static void setUp() {
        setUpTests();
    }

    @BeforeEach
    public void openPage() {
        openSite();
    }

    @AfterEach
    public void cleanUp() {
        Selenide.clearBrowserCookies();
        ApiService apiService = new ApiService();
        apiService.deleteAllBooks();
    }

    @Test()
    @DisplayName("Проверка авторизации и открытия пустого профиля")
    public void checkAuthorizationAndOpenProfile() {
        ProfilePage profilePage = new ProfilePage();
        LoginPage loginPage = new LoginPage();
        step("Открыть страницу '/login' и авторизоваться",
                loginPage::auth
        );
        step("Убедиться, что находимся на странице '/profile'",
                () -> profilePage.checkCurrentUrl(profilePage.getCurrentUrl())
        );
        step("Проверить, что таблица пустая",
                profilePage::checkNoRowsLabelIsVisible
        );
    }

    @Test
    @DisplayName("Проверка добавления 6 книг в профиль")
    public void checkAddingBooksToProfile() {
        BooksPage booksPage = new BooksPage();
        BookPage bookPage = new BookPage();
        ProfilePage profilePage = new ProfilePage();
        LoginPage loginPage = new LoginPage();
        int booksCount = 6;
        step("Открыть страницу '/login' и авторизоваться",
                loginPage::auth
        );
        step("Перейти на страницу 'Book Store'", () ->
                goToPage(bookStorePageButton)
        );
        step("Добавить в коллекцию 6 книг", () -> {
            for (int i = 0; i < booksCount; i++) {
                booksPage.clickBooks(i);
                bookPage.addBookToMyCollectionAndBackToStore();
            }
        });
        step("Перейти на страницу 'Profile'", () ->
                goToPage(profilePageButton)
        );
        step("Убедиться, что в таблице отображается 6 книг", () -> {
            profilePage.chooseRowsCount(RowsCount.ROWS_10);
            Assertions.assertEquals(booksCount, profilePage.getBookListInProfile().size());
        });
    }

    @Test
    @DisplayName("Проверка добавления книг в профиль и последующего удаления их из профиля")
    public void checkAddingBooksToProfileAndDeletingThem() {
        BooksPage booksPage = new BooksPage();
        BookPage bookPage = new BookPage();
        ProfilePage profilePage = new ProfilePage();
        LoginPage loginPage = new LoginPage();
        int booksCount = 2;
        step("Открыть страницу '/login' и авторизоваться",
                loginPage::auth
        );
        step("Перейти на страницу 'Book Store'", () ->
                goToPage(bookStorePageButton)
        );
        step("Добавить в коллекцию 2 книги", () -> {
            for (int i = 0; i < booksCount; i++) {
                booksPage.clickBooks(i);
                bookPage.addBookToMyCollectionAndBackToStore();
            }
        });

        step("Перейти на страницу 'Profile' и дождаться загрузки страницы", () -> {
            goToPage(profilePageButton);
            awaitUserNameLabelAppears();
        });
        step("Убедиться, что в таблице отображается 2 книги", () ->
                Assertions.assertEquals(booksCount, profilePage.getBookListInProfile().size())
        );
        step("Удалить все книги из профиля",
                profilePage::deleteAllBooks
        );
        step("Проверить, что в таблице нет книг",
                profilePage::checkNoRowsLabelIsVisible
        );
    }
}
