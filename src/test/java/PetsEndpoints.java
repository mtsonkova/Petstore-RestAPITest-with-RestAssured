import Payloads.PetsPayloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;

public class PetsEndpoints extends BaseAPITest {
    String apiKey = "qatest";

    //TODO string urls and tags are displayed as a code instead of text
    @Test(dataProvider = "petInfo")
    public void testAddPetEndpoint(String category, String petName, String[] url, String tags, String status) throws IOException {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        String response = given().header("Content-Type", "application/json")
                .body(PetsPayloads.addPetPayload(category, petName, url, tags, status))
                .when().post("/pet")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();

        ReusableMethods.createFileAppend("pets1.txt", response);
    }

    /*
    @Test(dataProvider = "petInfo2")
    public void testAddPetEndpoint2(String category, String petName, String[] url, String tags, String status) throws IOException {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        String response = given().header("Content-Type", "application/json")
                .body(PetsPayloads.addPetPayload2(category, petName,  url, tags, status))
                .when().post("/pet")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();


        ReusableMethods.createFileAppend("pets2.txt", response);
    }
*/
    @Test
    public void testUploadPhotoEndpoint() {
        String response = given().pathParam("petId", "9223372036854775807")
                .header("accept", "application/json")
                .header("Content-Type", "multipart/form-data")
                .multiPart("file", "image/jpeg")
                .when().post("/pet/{petId}/uploadImage")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
    }

    @Test
    public void findAllPetsWithStatusSold() throws IOException {
        String response = given().queryParam("status", "sold")
                .when().get("/pet/findByStatus")
                .then().assertThat().log().all().statusCode(200).extract().response().asPrettyString();

        ReusableMethods.createFileNoAppend("soldPets.txt", response);

    }

    @Test
    public void findAllPetsWithStatusPending() throws IOException {
        String response = given().queryParam("status", "pending")
                .when().get("/pet/findByStatus")
                .then().assertThat().log().all().statusCode(200).extract().response().asPrettyString();
        ReusableMethods.createFileNoAppend("pendingPets.txt", response);
    }

    @Test
    public void findAllPetsWithStatusAvailable() throws IOException {
        String response = given().queryParam("status", "available")
                .when().get("/pet/findByStatus")
                .then().assertThat().statusCode(200).extract().response().asPrettyString();
        ReusableMethods.createFileNoAppend("availablePets.txt", response);

        JsonPath jsonPath = ReusableMethods.convertStringToJSON(response);
        int size = jsonPath.getList("").size();
        Assert.assertTrue(size > 0);
    }

    //Test is supposed to fail with error code 400 Invalid status value
    // Instead it returns 200 OK with empty array
    @Test
    public void findPetByInvalidStatusValueShouldReturn400() {
        String response = given().queryParam("status", "purchased")
                .when().get("/pet/findByStatus")
                .then().log().all().assertThat().statusCode(400).extract().response().asPrettyString();
    }

    @Test
    public void findPetById() {
        String response = given()
                .header("accept", "application/json")
                .header("api_key", "qatest")
                .pathParam("petId", "100")
                .when().get("/pet/{petId}")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
    }

    @Test
    public void findPetByIncorrectId() {
        String response = given().pathParam("petId", "-25")
                .when().get("/pet/{petId}")
                .then().log().all().assertThat().statusCode(404).extract().response().asPrettyString();
    }

    @Test
    public void findPetByInvalidIDSupplied() {
        String response = given().pathParam("petId", "-65")
                .when().get("/pet/{petId}")
                .then().log().all().assertThat().statusCode(404).extract().response().asPrettyString();

        Assert.assertTrue(response.contains("Pet not found"));
    }

    @Test
    public void updatePetByFormData() {
        String response = given().header("accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .pathParam("petId", "100")
                .formParam("name", "GoldieLocks")
                .formParam("status", "sold")
                .when().post("/pet/{petId}")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
    }

    @Test
    public void deletePetByIdValidIdProvided() throws IOException {
        String petId = ReusableMethods.readAvailablePetsFile();
        System.out.println(petId);

        Response response = given().pathParam("petId", 455)
                .when().delete("/pet/{petId}");
        int statusCode = response.getStatusCode();
        String message = response.getStatusLine();
        Assert.assertTrue(statusCode == 404);
        Assert.assertTrue(message.contains("Not Found"));

    }
}





