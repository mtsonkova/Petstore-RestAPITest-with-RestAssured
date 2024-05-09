import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class UserEndpoints extends BaseAPITest {

    @Test
    public void createMultipleUsersWithJsonFile() throws IOException {

        Response response = ReusableMethods.createMultipleUserProfilesWithJsonFile();

        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200);
    }

    @Test
    public void getUserByUserName() throws IOException {
        Response newUsers = ReusableMethods.createMultipleUserProfilesWithJsonFile();

       String response = given().pathParam("username", "jsmith")
                .when().get("/user/{username}")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
    }

    @Test
    public void logUser() {
        String response = given()
                .queryParam("username", "jsmith")
                .queryParam("password", "password2")
                .when().get("/user/login")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asPrettyString();
    }



}
