import Payloads.PetsPayloads;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import static io.restassured.RestAssured.*;

public class PetsEndpoints extends BaseAPITest {

    @Test(dataProvider = "petInfo")
    public void testAddPetEndpoint(String category, String petName, String[] url, String[] tags, String status) throws IOException {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        String response = given().header("Content-Type", "application/json")
                .body(PetsPayloads.addPetPayload(category, petName, url, tags, status))
                .when().post("/pet")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        FileWriter fileWriter = new FileWriter("pets.json");
        fileWriter.write(response);
        fileWriter.flush();
    }
}





