package pagesTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.JiraMainPage;
import pages.JiraResultPage;
import webHooks.WebHooks;

public class Tests extends WebHooks {

    private final JiraMainPage jiraMainPage = new JiraMainPage();
    private final JiraResultPage jiraResultPage = new JiraResultPage();
    private String userLogin = "AT16", userPass = "Qwerty123";

    @Test
    @DisplayName("Тест #1. Аутентификация пользователя")
    public void authorizationTest() {
        Assertions.assertTrue(jiraMainPage
                .authorizeUser(userLogin, userPass)
                .getProfile()
                .getAttribute("title").contains(userLogin));
    }

    @Test
    @DisplayName("Тест #2. Переход в проект Test")
    public void goToTestProjectTest() {
        jiraMainPage.authorizeUser(userLogin, userPass).openTestProject();

        Assertions.assertTrue(jiraResultPage.checkLinkContainsText("TEST/summary"));
    }

    @Test
    @DisplayName("Тест #3. Проверка количества задач")
    public void checkTasksNumberTest() {
        jiraMainPage.authorizeUser(userLogin, userPass).openTestProject();

        Assertions.assertTrue(jiraResultPage.checkCounter());
    }

    @Test
    @DisplayName("Тест #4. Проверка задачи TestSeleniumATHomework")
    public void checkTestSeleniumATHomeworkTest() {
        jiraMainPage.authorizeUser(userLogin, userPass).openTestProject();
        jiraResultPage.searchTaskByName("TestSeleniumATHomework");

        Assertions.assertEquals("СДЕЛАТЬ", jiraResultPage.getTaskStatus().getText());
        Assertions.assertEquals("Version 2.0", jiraResultPage.getTaskVersion().getText());
    }

    @Test
    @DisplayName("Тест #5. Создание задачи")
    public void createNewTaskTest () {
        jiraMainPage.authorizeUser(userLogin, userPass).openTestProject();
        jiraResultPage.createNewBug();
        jiraResultPage.openLastBug();
        jiraResultPage.makeStatusFinal();
    }
}
