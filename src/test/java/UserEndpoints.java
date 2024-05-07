import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class UserEndpoints extends BaseAPITest{

    @Test
    public void createMultipleUsersWithJsonFile() throws IOException {
        // Specify the path to your JSON file
        String jsonFilePath = System.getProperty("user.dir") + "/src/test/java/utilities/userDetails.json";

        // Read the content of the JSON file as a string
        String jsonBody = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/user/createWithList");

        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200);
    }


}
