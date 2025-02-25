package tests;

import api.models.UserModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfigReader;
import utils.JsonReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ReqresTests {

    private static final String url = ConfigReader.getProperty("urlReqres");

    @Test
    @DisplayName("Тест #1. Создать пользователя на сайте reqres.in")
    public void createUserTest() throws IOException {
        UserModel user = JsonReader.readJsonFile();
        user.setName("Tomato");
        user.setJob("Eat maket");

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

        Assertions.assertEquals(user.getName(), user2.getName());
        Assertions.assertEquals(user.getJob(), user2.getJob());
    }
}
