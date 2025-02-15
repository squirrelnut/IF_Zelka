package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class JiraResultPage {
    private final SelenideElement profile = $x("//a[@data-username='AT16']").as("Ссылка на профиль юзера");
    private final SelenideElement projectsLink = $x("//a[@id='browse_link']").as("Ссылка на проекты");
    private final SelenideElement testProjectLink = $x("//ul[@class='aui-list-truncate']//a[@href='/browse/TEST']").as("ссылка на проект Тест");
    private final SelenideElement testProjectTitleLink = $x("//div[@class='aui-item project-title']//a").as("Title проекта Тест");
    private final SelenideElement allTasks = $x("//div[@class='showing']//span").as("Поле счетчика");
    private final SelenideElement searchForm = $x("//input[@id='quickSearchInput']").as("Поле поиска");
    private final SelenideElement taskStatus = $x("//span[@id='status-val']//span").as("Статус задачи");
    private final SelenideElement taskVersion = $x("//span[@id='fixVersions-field']//a").as("Версия задачи");
    private final SelenideElement createLink = $x("//a[@id='create_link']").as("Кнопка Создать");
    private final SelenideElement listTypes = $x("//div[@id='issuetype-single-select']").as("выпад. меню Тип задачи");
    private final ElementsCollection visualButtons = $$x("//button[text()='Визуальный']").as("кнопки Визуальный");
    private final SelenideElement tema = $x("//input[@id='summary']").as("Поле Тема");
    private final SelenideElement input = $x("//input[@id='issuetype-field']").as("Меню Тип задачи");
    private final SelenideElement selectedElement = $x("//div[@id='issuetype-options']").as("Пункты меню Тип задачи");
    private final SelenideElement version = $x("//option[@value='10001']").as("Поле Версия");
    private final SelenideElement assignToMeButton = $x("//button[@id='assign-to-me-trigger']").as("Назначить на меня");
    private final SelenideElement createButton = $x("//input[@id='create-issue-submit']").as("Кнопка Создать в диалоге");
    private final SelenideElement filter = $x("//button[@id='subnav-trigger']").as("Кнопка Фильтра");
    private final SelenideElement filter2 = $x("//ul[@class='aui-list-truncate']//a[text()='Мои открытые задачи']").as("пункт в Фильтре = Мои открытые задачи");
    private final SelenideElement inProgressStatus = $x("//a[@id='action_id_21']").as("Кнопка В работе");
    private final SelenideElement businessProcess = $x("//a[@id='opsbar-transitions_more']").as("Кнопка Бизнес-процесс");
    private final SelenideElement readyStatus = $x("//span[text()='Выполнено']").as("кнопка Выполнено");

    public void createNewBug() {
        createLink.click();
        listTypes.click();

        selectBugInTypeTask();
        tema.setValue("New bug!");
        version.click();
        assignToMeButton.click();
        visualButtons.get(0).click();
        visualButtons.get(1).click();
        createButton.click();
    }

    public void selectBugInTypeTask() {
        String str = selectedElement.getAttribute("data-suggestions");
        str = str.substring(0, str.indexOf("true"));
        str = str.substring(str.lastIndexOf("{"));
        if (str.contains("Задача") || str.contains("История")) {
            input.sendKeys(Keys.DOWN);
            input.sendKeys(Keys.ENTER);
        } else if (str.contains("Epic")) {
            input.sendKeys(Keys.DOWN);
            input.sendKeys(Keys.DOWN);
            input.sendKeys(Keys.ENTER);
        }
    }

    public void makeStatusFinal() {
        filter.click();
        filter2.click();

        inProgressStatus.shouldBe(visible, Duration.ofSeconds(5)).click();
        businessProcess.shouldBe(visible, Duration.ofSeconds(5)).click();
        businessProcess.click();
        readyStatus.shouldBe(visible, Duration.ofSeconds(15)).click();
    }

    public void openTestProject() {
        projectsLink.click();
        testProjectLink.click();
    }

    public boolean checkLinkContainsText(String text) {
        return testProjectTitleLink.getAttribute("href").contains(text);
    }

    public void searchTaskByName(String nameTask) {
        searchForm.setValue(nameTask).pressEnter();
    }

    public int getAllTasksNumber() {
        String str = allTasks.getText();
        str = str.substring(str.indexOf('з')+2);

        return Integer.parseInt(str);
    }

    public boolean checkCounter() {
        int counter1 = getAllTasksNumber();
        createNewBug();
        Selenide.refresh();
        int counter2 = getAllTasksNumber();

        return counter2-counter1 == 1;
    }

    public SelenideElement getProfile() {
        return profile;
    }

    public SelenideElement getTaskStatus() {
        return taskStatus;
    }

    public SelenideElement getTaskVersion() {
        return taskVersion;
    }
}