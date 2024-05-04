package utilities;

import io.restassured.path.json.JsonPath;

import java.io.FileWriter;
import java.io.IOException;

public class ReusableMethods {

    public static void createFileNoAppend(String fileName, String response) throws IOException {
        FileWriter fileWriter = new FileWriter("src/test/OutputFiles/" + fileName);
        fileWriter.write(response);
        fileWriter.flush();
    }

    public static void createFileAppend(String fileName, String response) throws IOException {
        FileWriter fileWriter = new FileWriter("src/test/OutputFiles/" + fileName, true);
        fileWriter.write(response);
        fileWriter.flush();
    }

    public static JsonPath convertStringToJSON(String text) {
        JsonPath jsonPath = new JsonPath(text);
        return jsonPath;
    }
}
