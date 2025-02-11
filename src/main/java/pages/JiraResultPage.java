package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class JiraResultPage {
    private final SelenideElement profile = $x("//a[@data-username='AT16']");
    private final SelenideElement projectsLink = $x("//a[@id='browse_link']");
    private final SelenideElement testProjectLink = $x("//ul[@class='aui-list-truncate']//a[@href='/browse/TEST']");
    private final SelenideElement testProjectTitleLink = $x("//div[@class='aui-item project-title']//a");
    private final SelenideElement allTasks = $x("//div[@class='showing']//span");
    private final SelenideElement searchForm = $x("//input[@id='quickSearchInput']");
    private final SelenideElement taskStatus = $x("//span[@id='status-val']//span");
    private final SelenideElement taskVersion = $x("//span[@id='fixVersions-field']//a");
    private final SelenideElement createLink = $x("//a[@id='create_link']");
    private final SelenideElement listTypes = $x("//div[@id='issuetype-single-select']");
    private final ElementsCollection visualButtons = $$x("//button[text()='Визуальный']");
    private final SelenideElement tema = $x("//input[@id='summary']");
    private final SelenideElement input = $x("//input[@id='issuetype-field']");
    private final SelenideElement selectedElement = $x("//div[@id='issuetype-options']");
    private final SelenideElement version = $x("//option[@value='10001']");
    private final SelenideElement assignToMeButton = $x("//button[@id='assign-to-me-trigger']");
    private final SelenideElement createButton = $x("//input[@id='create-issue-submit']");
    private final SelenideElement filter = $x("//button[@id='subnav-trigger']");
    private final SelenideElement filter2 = $x("//ul[@class='aui-list-truncate']//a[text()='Мои открытые задачи']");
    private final SelenideElement inProgressStatus = $x("//a[@id='action_id_21']");
    private final SelenideElement businessProcess = $x("//a[@id='opsbar-transitions_more']");
    private final SelenideElement doneStatus = $x("//span[text()='Исполнено']");
    private final SelenideElement readyStatus = $x("//span[text()='Выполнено']");
    private final SelenideElement doneButton = $x("//input[@value='Исполнено']");



    public void createNewBug() {
        createLink.click();
        listTypes.click();

        // выбор "Тип задачи" = "Ошибка"
        // не получилось сделать через селектор из-за динамического dropdown
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
        } else {
            // ничего не делаем, т.к. уже выбрана "Ошибка"
        }

        tema.setValue("New bug!");
        version.click();
        assignToMeButton.click();
        visualButtons.get(0).click();
        visualButtons.get(1).click();
        createButton.click();


        // Попытка выбрать тип задачи = "Ошибка" через JavaScript
        //Selenide.executeJavaScript("arguments[0].click()", $x("(//ul[@class='aui-last']//a)[2]"));

        // Попытка выбрать тип задачи = "Ошибка" через перебор пунктов списка
//        for (SelenideElement element : bugType) {
//            System.out.println(element);
//            System.out.println(element.getText());;
//
//            element.shouldBe(visible, Duration.ofSeconds(10));
//
//            if(element.getText().contains("Ошибка")) {
//                System.out.println("click !!!");
//                element.click();
//                break;
//            }
//        }
//        Selenide.actions().sendKeys(Keys.ENTER);
//        Selenide.actions().sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER);

    }

    public void makeStatusFinal() {
        filter.click();
        filter2.click();

        sleep(5000);
        inProgressStatus.shouldBe(visible, Duration.ofSeconds(10));
        inProgressStatus.click();

        sleep(5000);
        businessProcess.click();
        doneStatus.click();
        doneButton.click();

        sleep(5000);
        businessProcess.click();
        readyStatus.click();
    }

    public void openTestProject() {
        projectsLink.click();
        testProjectLink.click();
    }

    public int getAllTasksNumber() {
        String str = allTasks.getText(); // получаем строку с задачами
        str = str.substring(str.indexOf('з')+2); // обрезаем строку

        return Integer.parseInt(str);
    }

    public SelenideElement getSearchForm() {
        return searchForm;
    }

    public SelenideElement getProfile() {
        return profile;
    }

    public SelenideElement getTestProjectTitleLink() {
        return testProjectTitleLink;
    }

    public SelenideElement getTaskStatus() {
        return taskStatus;
    }

    public SelenideElement getTaskVersion() {
        return taskVersion;
    }
}
