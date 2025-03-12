package api.steps;

import api.models.CharacterModel;
import api.specifications.RickAndMortySpecifications;
import io.qameta.allure.Allure;
import org.apache.http.HttpStatus;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static io.restassured.RestAssured.given;

public class RickAndMortySteps {
    private static final String url = ConfigReader.getProperty("url");

    public int getLastEpisodeByCharacter(String nameCharacter) {
        Allure.step("Отправили запрос последнего эпизода с Морти");
        List<String> episodes = given()
                .spec(RickAndMortySpecifications.baseRequestSpec())
                .when()
                .get(url + "api/character/?name=" + nameCharacter)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().getList("results.episode", String.class);

        return getLastNumberFromList(episodes);
    }

    public int getLastCharacterFromEpisode(int number) {
        Allure.step("Отправили запрос о последнем персонаже");
        List<String> characters = given()
                .spec(RickAndMortySpecifications.baseRequestSpec())
                .when()
                .get(url + "api/episode/" + number)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().getList("characters", String.class);

        return getLastNumberFromList(characters);
    }

    public CharacterModel getDataFromCharacter(int number) {
        Allure.step("Отправили запрос о расе и локации персонажа с id = " + number);

        return given()
                .spec(RickAndMortySpecifications.baseRequestSpec())
                .when()
                .get(url + "api/character/" + number)
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().as(CharacterModel.class);
    }

    public void compareMortyAndCharacter(CharacterModel character, CharacterModel morty) {
        Allure.step("Сравниваем данные персонажей");
        Allure.step("Раса Морти = " + morty.getSpecies());
        Allure.step("Локация Морти = " + morty.getLocation().name);
        Allure.step("Расы " + checkField(morty.getSpecies(), character.getSpecies()));
        Allure.step("Локации " + checkField(morty.getLocation().name, character.getLocation().name));
    }

    private String checkField(String str1, String str2) {
        String answeer = "не совпадают";
        if (str1.equals(str2)) {
            answeer = "совпадают";
        }

        return answeer;
    }

    public int getIDByCharacterName(String nameCharacter) {
        Allure.step("Получаю id персонажа по имени " + nameCharacter);
        return given()
                .spec(RickAndMortySpecifications.baseRequestSpec())
                .when()
                .get(url + "api/character/?name=" + nameCharacter)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().get("results[0].id");
    }

    private int getLastNumberFromList(List<String> links) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String s : links) {
            StringTokenizer tokenizer = new StringTokenizer(s, ",");

            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                token = token.substring(token.lastIndexOf("/") + 1);
                Character ch = token.charAt(token.length() - 1);

                if (ch.equals(']')) {
                    token = token.substring(0, token.length() - 1);
                }

                numbers.add(Integer.valueOf(token));
            }
        }

        numbers.sort(Collections.reverseOrder());
        return numbers.get(0);
    }
}
