package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class JiraMainPage {
    private final SelenideElement nameForm = $x("//input[@id='login-form-username']").as("Строка ввода имени");
    private final SelenideElement passForm = $x("//input[@id='login-form-password']").as("Строка ввода пароля");
    private final SelenideElement enterButton = $x("//input[@id='login']").as("Кнопка Войти");


    public JiraResultPage authorizeUser () {
        nameForm.setValue("AT16");
        passForm.setValue("Qwerty123");
        enterButton.click();
        return new JiraResultPage();
    }
}
