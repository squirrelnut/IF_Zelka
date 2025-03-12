package tests;

import api.models.UserModel;
import api.steps.ReqresSteps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@DisplayName("Набор тестов для сайта reqres.in (rest-assured)")
public class ReqresTests {

    @Test
    @DisplayName("Тест #1. Создать пользователя на сайте reqres.in")
    public void createUserTest() throws IOException {
        ReqresSteps reqresSteps = new ReqresSteps();
        UserModel user = reqresSteps.prepareUserData();
        UserModel user2 = reqresSteps.createUser(user);

        Assertions.assertEquals(user.getName(), user2.getName());
        Assertions.assertEquals(user.getJob(), user2.getJob());
    }
}
