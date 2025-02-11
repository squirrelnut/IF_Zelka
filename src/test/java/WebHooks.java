import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    @BeforeEach
    public void initBrowser() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;
        Configuration.browser = Browsers.CHROME;
        open("https://edujira.ifellow.ru/");
        getWebDriver().manage().window().maximize();
    }

    @AfterEach
    public void afterTest() {

        Selenide.closeWebDriver();
    }
}
