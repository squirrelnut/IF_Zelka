package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class JiraMainPage {
    private final SelenideElement nameForm = $x("//input[@id='login-form-username111']").as("Поле ввода имени");
    private final SelenideElement passForm = $x("//input[@id='login-form-password']").as("Поле ввода пароля");
    private final SelenideElement enterButton = $x("//input[@id='login']").as("Кнопка Войти");

    @Step("Авторизоваться в системе под пользователем '{login}'")
    public JiraResultPage authorizeUser (String login, String pass) {
        Allure.step("Заходим в систему под пользователем " + login);
        nameForm.setValue(login);
        passForm.setValue(pass);
        enterButton.click();
        return new JiraResultPage();
    }
}
