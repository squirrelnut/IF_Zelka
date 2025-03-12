package tests;

import api.models.CharacterModel;
import api.specifications.RickAndMortySpecifications;
import api.steps.RickAndMortySteps;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Набор тестов для сайта rickandmortyapi.com (rest-assured)")
public class RickAndMortyTests {
    RickAndMortySteps step = new RickAndMortySteps();

    @BeforeEach
    public void setUp() {
        RestAssured.requestSpecification = RickAndMortySpecifications.baseRequestSpec();
        RestAssured.responseSpecification = RickAndMortySpecifications.baseResponseSpecSuccess();
    }

    @Test
    @DisplayName("Тест #1. Получить последний эпизод с персонажем Морти Смит")
    public void getLastEpisodeWithMorty() {
        int lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");

        Allure.step("Получили id последнего эпизода с Морти = " + lastEpisode);
    }

    @Test
    @DisplayName("Тест #2. Получить из списка последнего эпизода последнего персонажа")
    public void getLastCharacter() {
        int lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");
        Allure.step("Получили id последнего эпизода с Морти = " + lastEpisode);
        int lastCharacter = step.getLastCharacterFromEpisode(lastEpisode);
        Allure.step("id последнего персонажа из последнего эпизода с Морти = " + lastCharacter);
    }

    @Test
    @DisplayName("Тест #3. Получить данные по местонахождению и расе последнего персонажа")
    public void getDataOfLastCharacter() {
        int lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");
        Allure.step("Получили id последнего эпизода с Морти = " + lastEpisode);
        int lastCharacter = step.getLastCharacterFromEpisode(lastEpisode);
        Allure.step("id последнего персонажа из последнего эпизода с Морти = " + lastCharacter);
        CharacterModel character = step.getDataFromCharacter(lastCharacter);

        Allure.step("Раса послед. персонажа: " + character.getSpecies());
        Allure.step("Место послед. персонажа: " + character.getLocation().name);
    }

    @Test
    @DisplayName("Тест #4. Сравнить расу и местонахождение Морти с последним персонажем")
    public void compareMortyAndLastCharacter() {
        int lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");
        Allure.step("Получили id последнего эпизода с Морти = " + lastEpisode);
        int lastCharacter = step.getLastCharacterFromEpisode(lastEpisode);
        Allure.step("id последнего персонажа из последнего эпизода с Морти: " + lastCharacter);
        CharacterModel character = step.getDataFromCharacter(lastCharacter);
        Allure.step("Раса послед. персонажа: " + character.getSpecies());
        Allure.step("Место послед. персонажа: " + character.getLocation().name);
        int idMorty = step.getIDByCharacterName("Morty Smith");
        CharacterModel morty = step.getDataFromCharacter(idMorty);
        step.compareMortyAndCharacter(character, morty);
    }
}
