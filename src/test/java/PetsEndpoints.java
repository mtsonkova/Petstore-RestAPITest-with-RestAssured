import Payloads.PetsPayloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import static io.restassured.RestAssured.*;

public class PetsEndpoints extends BaseAPITest {
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

    @Test(dataProvider = "petInfo2")
    public void testAddPetEndpoint2(String category, String petName, String url, String tags, String status) throws IOException {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        String response = given().header("Content-Type", "application/json")
                .body(PetsPayloads.addPetPayload2(category, petName, url, tags, status))
                .when().post("/pet")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();


        ReusableMethods.createFileAppend("pets2.txt", response);
    }

    @Test
    public void testUploadPhotoEndpoint() {
        String response = given().pathParam("petId", "9223372036854775807")
                .header("accept","application/json")
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
     }



}





