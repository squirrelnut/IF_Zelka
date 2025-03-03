package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterModel {
    private Integer id;
    private String name;
    private String species;
    private LocationCharacter location;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public LocationCharacter getLocation() {
        return location;
    }

    public void setLocation(LocationCharacter location) {
        this.location = location;
    }
}

