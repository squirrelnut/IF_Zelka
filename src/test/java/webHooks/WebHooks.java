package webHooks;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import utils.ConfigReader;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    @BeforeEach
    public void initBrowser() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;
        Configuration.browser = Browsers.CHROME;
        open(ConfigReader.getProperty("url"));
        getWebDriver().manage().window().maximize();

        SelenideLogger.addListener("AllureListener", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterEach
    public void afterTest() {

        Selenide.closeWebDriver();
    }
}
