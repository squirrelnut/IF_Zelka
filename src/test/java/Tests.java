import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.JiraMainPage;
import pages.JiraResultPage;

public class Tests extends WebHooks {

    private final JiraMainPage jiraMainPage = new JiraMainPage();
    private final JiraResultPage jiraResultPage = new JiraResultPage();
    private String check;

    @Test
    @DisplayName("Тест #1. Авторизация")
    public void authorizationTest() {
        Assertions.assertTrue(jiraMainPage
                .authorizeUser()
                .getProfile()
                .getAttribute("title").contains("AT16"));
        // код ниже делает тоже самое, только не в одну строку
//        jiraMainPage.authorizeUser();
//        check = jiraResultPage.getProfile().getAttribute("title");
//        Assertions.assertTrue(check.contains("AT16"));
    }

    @Test
    @DisplayName("Тест #2. Переход в проект Test")
    public void goToTestProjectTest() {
        jiraMainPage.authorizeUser().openTestProject();
        check = jiraResultPage.getTestProjectTitleLink().getAttribute("href");

        Assertions.assertTrue(check.contains("TEST/summary"));
        //Assertions.assertEquals("https://edujira.ifellow.ru/projects/TEST/summary", check);
    }

    @Test
    @DisplayName("Тест #3. Проверка количества задач")
    public void checkTasksNumberTest() throws InterruptedException {
        jiraMainPage.authorizeUser().openTestProject(); // авторизация, переход на проект Test
        int counter1 = jiraResultPage.getAllTasksNumber(); // читаем счетчик
        jiraResultPage.createNewBug(); // создаем новый баг
        Selenide.refresh(); // обновляем страницу
        int counter2 = jiraResultPage.getAllTasksNumber();  // читаем счетчик

        //System.out.println(counter1 + " ! = " + counter2);
        Assertions.assertTrue(counter2 - counter1 == 1);
    }

    @Test
    @DisplayName("Тест #4. Проверка задачи TestSeleniumATHomework")
    public void checkTestSeleniumATHomeworkTest() {
        jiraMainPage.authorizeUser().openTestProject();
        jiraResultPage.getSearchForm().setValue("TestSeleniumATHomework").pressEnter();

        Assertions.assertEquals("СДЕЛАТЬ", jiraResultPage.getTaskStatus().getText());
        Assertions.assertEquals("Version 2.0", jiraResultPage.getTaskVersion().getText());
    }

    @Test
    @DisplayName("Тест #5. Создание задачи")
    public void createNewTaskTest () {
        jiraMainPage.authorizeUser().openTestProject();
        jiraResultPage.createNewBug(); // создание бага
        jiraResultPage.makeStatusFinal(); // продвижение по статусам
    }
}
