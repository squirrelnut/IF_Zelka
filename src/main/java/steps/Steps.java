package steps;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.PageLoadStrategy;
import pages.JiraMainPage;
import pages.JiraResultPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Steps {
    private JiraResultPage jiraResultPage = new JiraResultPage();
    private String login = "AT16";
    private String pass = "Qwerty123";
    private int counter1 = 0;

    @BeforeAll
    public static void beforeAllScenarios() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;
        Configuration.browser = Browsers.CHROME;
        open("https://edujira.ifellow.ru/");
        getWebDriver().manage().window().maximize();
    }

    @AfterAll
    public static void afterAllScenarios() {
        Selenide.closeWebDriver();
    }

    @Когда("пользователь вводит корректные логин и пароль")
    public void setLoginPass() {
        jiraResultPage = new JiraMainPage().authorizeUser(login, pass);
    }

    @Тогда("открывается страница с профилем пользователя")
    public void checkUserProfile() {
        jiraResultPage.getProfile().getAttribute("title").contains("AT16");
    }

    @Когда("пользователь нажимает на ссылку проекта Test")
    public void openTestProject() {
        jiraResultPage.openTestProject();
    }

    @Тогда("открывается summary проекта Test")
    public void checkTitleOfTestProject() {
        Assertions.assertTrue(jiraResultPage.checkLinkContainsText("TEST/summary"));
    }

    @Когда("пользователь вводит в поиск {string} и нажимает Enter")
    public void searchTask(String name) {
        jiraResultPage.searchTaskByName(name);
    }

    @Тогда("открывается задача TestSeleniumATHomework и проверяются ее поля")
    public void checkTasksFields() {
        Assertions.assertEquals("СДЕЛАТЬ", jiraResultPage.getTaskStatus().getText());
        Assertions.assertEquals("Version 2.0", jiraResultPage.getTaskVersion().getText());
    }

    @Когда("пользователь видит счетчик задач и создает новую задачу")
    public void getCounterNumber() {
        counter1 = jiraResultPage.getAllTasksNumber();
        createNewBug();
    }

    @Тогда("проверяем, что счетчик задач увеличился на 1")
    public void checkCounter() {
        Selenide.refresh();
        int counter2 = jiraResultPage.getAllTasksNumber();
        Assertions.assertEquals(1, counter2 - counter1);
    }

    @Когда("пользователь создает баг")
    public void createNewBug() {
        jiraResultPage.createNewBug();
    }

    @И("переводит его в финальный статус")
    public void makeFinalStatus() {
        jiraResultPage.openLastBug2();
        jiraResultPage.makeStatusFinal2();
    }
}