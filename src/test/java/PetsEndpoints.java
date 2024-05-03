import Payloads.PetsPayloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import static io.restassured.RestAssured.*;

public class PetsEndpoints extends BaseAPITest {
//TODO string urls and tags are displayed as a code instead of text
    @Test(dataProvider = "petInfo")
    public void testAddPetEndpoint(String category, String petName, String[] url, String[] tags, String status) throws IOException {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
       String response = given().header("Content-Type", "application/json")
                .body(PetsPayloads.addPetPayload(category, petName, url, tags, status))
                .when().post("/pet")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();


        FileWriter fileWriter = new FileWriter("pets.txt", true);
        fileWriter.write(response);
        fileWriter.flush();
    }

    @Test
    public void findAllPetsWithStatusSold() {
        String response = given().queryParam("status", "sold")
                .when().get("/pet/findByStatus")
                .then().assertThat().log().all().statusCode(200).extract().response().asPrettyString();
    }

    @Test
    public void findAllPetsWithStatusPending() {
        String response = given().queryParam("status", "pending")
                .when().get("/pet/findByStatus")
                .then().assertThat().log().all().statusCode(200).extract().response().asPrettyString();
    }

    @Test
    public void findAllPetsWithStatusAvailable() {
        String response = given().queryParam("status", "available")
                .when().get("/pet/findByStatus")
                .then().assertThat().log().all().statusCode(200).extract().response().asPrettyString();
    }

}





