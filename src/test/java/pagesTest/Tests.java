package pagesTest;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.JiraMainPage;
import pages.JiraResultPage;
import utils.ConfigReader;
import webHooks.WebHooks;

@DisplayName("Набор тестов для Jira")
public class Tests extends WebHooks {

    private final JiraMainPage jiraMainPage = new JiraMainPage();
    private final JiraResultPage jiraResultPage = new JiraResultPage();
    private final String userLogin = ConfigReader.getProperty("login");
    private final String userPass = ConfigReader.getProperty("pass");

    @Test
    @DisplayName("Тест #1. Аутентификация пользователя")
    @Description("Проверка успешной аутентификации и названия профиля")
    public void authorizationTest() {
        Allure.addAttachment("Пользователь ", userLogin);
        Assertions.assertTrue(jiraMainPage
                .authorizeUser(userLogin, userPass)
                .getProfile()
                .getAttribute("title").contains(userLogin));
    }

    @Test
    @DisplayName("Тест #2. Переход в проект Test")
    @Description("Проверка перехода в проект Тест")
    public void goToTestProjectTest() {
        jiraMainPage.authorizeUser(userLogin, userPass).openTestProject();

        Assertions.assertTrue(jiraResultPage.checkLinkContainsText("TEST/summary"));
    }

    @Test
    @DisplayName("Тест #3. Проверка количества задач")
    @Description("Проверка увеличения счетчика на 1 после создания новой задачи")
    public void checkTasksNumberTest() {
        jiraMainPage.authorizeUser(userLogin, userPass).openTestProject();

        Assertions.assertTrue(jiraResultPage.checkCounter());
    }

    @Test
    @DisplayName("Тест #4. Проверка задачи TestSeleniumATHomework")
    @Description("Проверка статуса и версии в задаче TestSeleniumATHomework")
    public void checkTestSeleniumATHomeworkTest() {
        jiraMainPage.authorizeUser(userLogin, userPass).openTestProject();
        jiraResultPage.searchTaskByName("TestSeleniumATHomework");

        jiraResultPage.checkFields();
    }

    @Test
    @DisplayName("Тест #5. Создание нового бага")
    @Description("Тест создания нового бага и доведения его до финального статуса")
    public void createNewTaskTest () {
        jiraMainPage.authorizeUser(userLogin, userPass).openTestProject();
        jiraResultPage.createNewBug();
        jiraResultPage.makeStatusFinal();
    }
}