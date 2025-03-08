package api.steps;

import api.models.CharacterModel;
import api.specifications.RickAndMortySpecifications;
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

    // получение расы
    public String getSpecieFromCharacter(int number) {
        String specie = given()
                .spec(RickAndMortySpecifications.baseRequestSpec())
                .when()
                .get(url + "api/character/" + number)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().get("species");

        return specie;
    }

    public CharacterModel getDataFromCharacter(int number) {
        CharacterModel character =
                given()
                        .spec(RickAndMortySpecifications.baseRequestSpec())
                        .when()
                        .get(url + "api/character/" + number)
                        .then().log().all()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().body().as(CharacterModel.class);

        return character;
    }

    public void compareMortyAndCharacter(int lastCharacter, int mortyID) {
        CharacterModel character = getDataFromCharacter(lastCharacter);
        CharacterModel morty = getDataFromCharacter(mortyID);

        System.out.println("Расы " + checkField(morty.getSpecies(), character.getSpecies()));
        System.out.println("Локации " + checkField(morty.getLocation().name, character.getLocation().name));
    }

    private String checkField(String str1, String str2) {
        String answeer = "не совпадают";
        if (str1.equals(str2)) {
            answeer = "совпадают";
        }

        return answeer;
    }

    // получение места
    public String getLocationFromCharacter(int number) {
        String location = given()
                .spec(RickAndMortySpecifications.baseRequestSpec())
                .when()
                .get(url + "api/character/" + number)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().get("location.name");

        return location;
    }

    public int getIDByCharacterName(String nameCharacter) {
        return given()
                .spec(RickAndMortySpecifications.baseRequestSpec())
                .when()
                .get(url + "api/character/?name=" + nameCharacter)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath().get("results[0].id");
    }

    // вспомогательные методы
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

        Collections.sort(numbers, Collections.reverseOrder());
        return numbers.get(0);
    }
}
