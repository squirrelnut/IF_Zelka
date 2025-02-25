package tests;

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

        String specie = step.getSpecieFromCharacter(lastCharacter);
        String location = step.getLocationFromCharacter(lastCharacter);

        System.out.println("Раса послед. персонажа: " + specie);
        System.out.println("Место послед. персонажа: " + location);
    }

    @Test
    @DisplayName("Тест #4. Сравнить расу и местонахождение Морти с последним персонажем")
    public void compareMortyAndLastCharacter() {
        int lastEpisode = step.getLastEpisodeByCharacter("Morty Smith");
        int lastCharacter = step.getLastCharacterFromEpisode(lastEpisode);

        String specieLastCharacter = step.getSpecieFromCharacter(lastCharacter);
        String locationLastCharacter = step.getLocationFromCharacter(lastCharacter);

        int idMorty = step.getIDByCharacterName("Morty Smith");
        String specieMorty = step.getSpecieFromCharacter(idMorty);
        String locationMorty = step.getLocationFromCharacter(idMorty);

        if (specieMorty.equals(specieLastCharacter)) {
            System.out.println("Расы совпадают");
        } else {
            System.out.println("Расы НЕ совпадают");
        }

        if (locationMorty.equals(locationLastCharacter)) {
            System.out.println("Местонахождение совпадает");
        } else {
            System.out.println("Местонахождение НЕ совпадает");
        }
    }
}
