package tests;

import api.models.CharacterModel;
import api.specifications.RickAndMortySpecifications;
import api.steps.RickAndMortySteps;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RickAndMortyTests {
    RickAndMortySteps step = new RickAndMortySteps();

    @BeforeAll
    public static void setUp() {
        RestAssured.requestSpecification = RickAndMortySpecifications.baseRequestSpec();
        RestAssured.responseSpecification = RickAndMortySpecifications.baseResponseSpecSuccess();
    }

    @Test
    @DisplayName("Тест #1. Получить последний эпизод с персонажем Морти Смит")
    public void getLastEpisodeWithMorty() {
        int lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");
        System.out.println("Последний эпизод с персонажем Морти Смит: " + lastEpisode);
    }

    @Test
    @DisplayName("Тест #2. Получить из списка последнего эпизода последнего персонажа")
    public void getLastCharacter() {
        int lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");
        int lastCharacter = step.getLastCharacterFromEpisode(lastEpisode);
        System.out.println("id последнего персонажа из последнего эпизода с Морти: " + lastCharacter);
    }

    @Test
    @DisplayName("Тест #3. Получить данные по местонахождению и расе последнего персонажа")
    public void getDataOfLastCharacter() {
        int lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");
        int lastCharacter = step.getLastCharacterFromEpisode(lastEpisode);
        CharacterModel character = step.getDataFromCharacter(lastCharacter);

        System.out.println("Раса послед. персонажа: " + character.getSpecies());
        System.out.println("Место послед. персонажа: " + character.getLocation().name);
    }

    @Test
    @DisplayName("Тест #4. Сравнить расу и местонахождение Морти с последним персонажем")
    public void compareMortyAndLastCharacter() {
        int lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");
        int lastCharacter = step.getLastCharacterFromEpisode(lastEpisode);
        int idMorty = step.getIDByCharacterName("Morty Smith");
        step.compareMortyAndCharacter(lastCharacter, idMorty);
    }
}
