package steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;

import pages.JiraMainPage;
import pages.JiraResultPage;

public class Steps {
    public JiraResultPage resultPage;

    @Дано("открывается сайт {string}")
    public void openSite(String url) {
        Selenide.open(url);
    }

    @Если("пользователь вводит корректные логин и пароль")
    public void setLoginPass() {
        resultPage = new JiraMainPage().authorizeUser("AT16", "Qwerty123");
    }

    @Тогда("открывается страница с профилем пользователя")
    public void checkUserProfile() {
        resultPage.getProfile().getAttribute("title").contains("AT16");
    }


}
