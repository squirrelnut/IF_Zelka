package tests;

import api.models.UserModel;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfigReader;
import utils.FileReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@DisplayName("Набор тестов для сайта reqres.in")
public class ReqresTests {

    private static final String url = ConfigReader.getProperty("urlReqres");

    @BeforeEach
    public void setUp() {
        SelenideLogger.addListener("AllureListener", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @Test
    @DisplayName("Тест #1. Создать пользователя на сайте reqres.in")
    public void createUserTest() throws IOException {

        UserModel user = FileReader.readJsonFile();
        Allure.step("Прочитали из json-файла: поле name = " + user.getName());
        user.setName("Tomato");
        user.setJob("Eat maket");
        Allure.step("Заменили поля: name = " + user.getName() + ", job = " + user.getJob());

        UserModel user2 =
                given()
                        .contentType("application/json")
                        .log().body()
                        .body(user)
                        .when()
                        .post(url + "api/users")
                        .then().log().all()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract().body().as(UserModel.class);

        Allure.step("Сравниваем ответ: name: " + user.getName() + " = " + user2.getName());
        Allure.step("Сравниваем ответ: job: " + user.getJob() + " = " + user2.getJob());
        Assertions.assertEquals(user.getName(), user2.getName());
        Assertions.assertEquals(user.getJob(), user2.getJob());
    }
}
