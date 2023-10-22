import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static ru.inno.utils.Configurator.openSite;

public class DemoQATest {

    String loginUrl = "https://demoqa.com/login";

    @BeforeEach
    public void openPage(){
        openSite(loginUrl);
    }

    @Test
    public void demoQaTest(){
        $("#userName").sendKeys("Alyona");
        $("#password").sendKeys("Qwerty432!");
        $("#login").click();
        $("#userName-value").shouldHave(Condition.text("Alyona"));

    }
}
