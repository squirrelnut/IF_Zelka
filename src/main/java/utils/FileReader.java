package utils;

import api.models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileReader {

    public static UserModel readJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(ConfigReader.getProperty("jsonPath")), UserModel.class);
    }
}
