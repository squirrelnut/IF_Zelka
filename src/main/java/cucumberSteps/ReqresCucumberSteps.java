package cucumberSteps;

import api.models.UserModel;
import api.steps.ReqresSteps;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class ReqresCucumberSteps {

    UserModel user, user2;

    @Когда("меняю данные пользователя и отправляю на сервер")
    public void changeUserDataAndSend() throws IOException {
        ReqresSteps reqresSteps = new ReqresSteps();
        user = reqresSteps.prepareUserData();
        user2 = reqresSteps.createUser(user);
    }

    @Тогда("получаю данные от сервера и сверяю с запросом")
    public void getUserData() {
        Assertions.assertEquals(user.getName(), user2.getName());
        Assertions.assertEquals(user.getJob(), user2.getJob());
    }
}
