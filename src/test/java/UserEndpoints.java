import Payloads.UsersPayload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;

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

    @Test
    public void TestMultipleEndpoints_logUserCreateNewUserUpdateUserDeleteUser() {
        //log user
        given()
                .queryParam("username", "jsmith")
                .queryParam("password", "password2")
                .when().get("/user/login");
        System.out.println("User logged");

        //create new user

        String responseCreateNewUser = given().header("Content-Type", "application/json")
                .body(UsersPayload.createNewUser("sstone", "Sasha", "Stone",
                        "sstone@email.com", "12345678", "3245423432", 5))
                .when().post("/user")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
        System.out.println("New user created");
        //update newly created user

        String responseUpdateNewlyCreatedUser = given().header("Content-Type", "application/json")
                .pathParam("username", "sstone")
                .body(UsersPayload.createNewUser("ssmith", "Sasha", "Smith",
                        "ssmith2@email.com", "12345678", "3245423432", 3))
                .when().put("/user/{username}")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
            System.out.println("New user updated");
        // get newly updated user

        String responseUpdatedUser = given().pathParams("username", "ssmith")
                .when().get("/user/{username}")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
        System.out.println("Updated user fetchecd");
        // delete newly updated user

        String responseDeleteUpdatedUser = given().pathParams("username", "ssmith")
                .when().delete("/user/{username}")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
        System.out.println("Updated user deleted");

        //get deleted user
        String responseDeletedUser = given().pathParams("username", "ssmith")
                .when().get("/user/{username}")
                .then().log().all().assertThat().statusCode(404).extract().response().asPrettyString();
        System.out.println("Deleted user fetchecd");

        // log out user
        String responseLogOutUser = given()
                .when().get("/user/logout")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asPrettyString();
        System.out.println("User logged out");
    }


    @Test
    public void TestMultipleEndpoints_TriggerErrorCodes() {
        //log user
        given()
                .queryParam("username", "jsmith")
                .queryParam("password", "password2")
                .when().get("/user/login");
        System.out.println("User logged");



       String responseUpdateNewlyCreatedUser = given().header("Content-Type", "application/json")
                .pathParam("username", "anewman")
                .body(UsersPayload.createNewUser("asullivan", "Annette", "Sullivan",
                        "asul2@email.com", "12345678", "3245423432", 6))
                .when().put("/user/{username}")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
        System.out.println("New user updated");

        // delete newly updated user

        String responseDeleteUpdatedUser = given().pathParams("username", "jamesDonovanJS")
                .when().delete("/user/{username}")
                .then().log().all().assertThat().statusCode(404).extract().response().asPrettyString();
        System.out.println("Updated user deleted");

        //get deleted user
        String responseDeletedUser = given().pathParams("username", "ssmith")
                .when().get("/user/{username}")
                .then().log().all().assertThat().statusCode(404).extract().response().asPrettyString();
        System.out.println("Deleted user fetchecd");

        // log out user
        String responseLogOutUser = given()
                .when().get("/user/logout")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response().asPrettyString();
        System.out.println("User logged out");
    }
}
