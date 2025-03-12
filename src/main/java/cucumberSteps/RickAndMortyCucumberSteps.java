
package cucumberSteps;

import api.models.CharacterModel;
import api.steps.RickAndMortySteps;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;

public class RickAndMortyCucumberSteps {
    RickAndMortySteps step = new RickAndMortySteps();
    int lastEpisode = 0;
    int lastCharacter = 0;
    CharacterModel character;
    int idMorty;

    @Когда("запрашиваю последний эпизод с Морти Смит")
    public void getLastEpisode() {
        lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");
    }

    @Тогда("получаю id этого эпизода")
    public void getIdLastEpisode() {
        Allure.step("Получили id последнего эпизода с Морти = " + lastEpisode);
    }

    @Когда("запрашиваю из списка последнего эпизода последнего персонажа")
    public void getLastCharacter() {
        lastCharacter = step.getLastCharacterFromEpisode(lastEpisode);
    }

    @Тогда("получаю id последнего персонажа")
    public void getIDLastCharacter() {
        Allure.step("Получили id последнего персонажа = " + lastCharacter);
    }

    @Когда("запрашиваю расу и локацию последнего персонажа")
    public void getDataOfLastCharacter() {
        character = step.getDataFromCharacter(lastCharacter);
    }

    @Тогда("получаю расу и локацию последнего персонажа")
    public void getSpeciesLocationLastCharacter() {
        Allure.step("Раса послед. персонажа: " + character.getSpecies());
        Allure.step("Локация послед. персонажа: " + character.getLocation().name);
    }

    @Когда("ищу персонажа Morty Smith и сравниваю с последним персонажем")
    public void compareMortyAndLastCharacter() {
        idMorty = step.getIDByCharacterName("Morty Smith");

    }

    @Тогда("получаю результаты сравнения")
    public void compareResults() {
        CharacterModel morty = step.getDataFromCharacter(idMorty);
        step.compareMortyAndCharacter(character, morty);
    }
}
