
package cucumberSteps;

import api.steps.RickAndMortySteps;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import utils.ConfigReader;

public class RickAndMortyCucumberSteps {
    RickAndMortySteps step = new RickAndMortySteps();
    int answer = 0;
    private static final String url = ConfigReader.getProperty("url");

    @Когда("запрашиваю последний эпизод с Морти Смит")
    public void getLastEpisode() {

        answer = step.getLastEpisodeByCharacter("Morty Smith");
    }

    @Тогда("получаю id этого эпизода")
    @Step("получаю последний эпизод с персонажем Морти Смит")
    public void getLastEpisodeWithMorty() {
        System.out.println("Последний эпизод с персонажем Морти Смит: " + answer);
    }
}
