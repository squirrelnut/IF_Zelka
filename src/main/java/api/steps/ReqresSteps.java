package api.steps;

import api.models.UserModel;
import io.qameta.allure.Allure;
import org.apache.http.HttpStatus;
import utils.ConfigReader;
import utils.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ReqresSteps {

    public UserModel prepareUserData() throws IOException {
        UserModel user = FileReader.readJsonFile();
        Allure.step("Прочитали из json-файла: поле name = " + user.getName());

        user.setName("Tomato");
        user.setJob("Eat maket");
        Allure.step("Заменили поля: name = " + user.getName() + ", job = " + user.getJob());

        return user;
    }


    public UserModel createUser(UserModel userModel) throws IOException {
        Allure.step("Отправили запрос создания пользователя");
        UserModel user =
                given()
                        .contentType("application/json")
                        .log().body()
                        .body(userModel)
                        .when()
                        .post(ConfigReader.getProperty("urlReqres") + "api/users")
                        .then().log().all()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract().body().as(UserModel.class);

        Allure.addAttachment("Исходный json", Files.newInputStream(Paths.get("src/test/resources/createUser.json")));
        Allure.addAttachment("Ответ сервера: ", "name = " + user.getName() + ", job = " + user.getJob());

        return user;
    }


}
