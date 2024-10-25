package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

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

    public static String readOrderDetailsFile() throws IOException {
        File file = new File(
                System.getProperty("user.dir") + "/src/test/OutputFiles/orderDetails.txt");

        String orderId = "";

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            orderId = scanner.nextLine();
        }

        return orderId;
    }

    public static String readAvailablePetsFile() throws IOException {
        File file = new File(
                System.getProperty("user.dir") + "/src/test/OutputFiles/availablePets.txt");

        String line = "";

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            //"id": 9223372036854023176
            if(line.contains("id")) {
                break;
            }
        }

        String petId = line.substring(14, line.length()-1);
        return petId;
    }
}

