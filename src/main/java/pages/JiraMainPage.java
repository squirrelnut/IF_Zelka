package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class JiraMainPage {
    private final SelenideElement nameForm = $x("//input[@id='login-form-username']").as("Поле ввода имени");
    private final SelenideElement passForm = $x("//input[@id='login-form-password']").as("Поле ввода пароля");
    private final SelenideElement enterButton = $x("//input[@id='login']").as("Кнопка Войти");

    public JiraResultPage authorizeUser (String login, String pass) {
        nameForm.setValue(login);
        passForm.setValue(pass);
        enterButton.click();
        return new JiraResultPage();
    }
}
