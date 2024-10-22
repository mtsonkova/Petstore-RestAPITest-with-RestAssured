package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

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

    public static Response createMultipleUserProfilesWithJsonFile() throws IOException {
        String jsonFilePath = System.getProperty("user.dir") + "/src/test/java/utilities/userDetails.json";

        // Read the content of the JSON file as a string
        String jsonBody = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/user/createWithList");
        return response;
    }
}

