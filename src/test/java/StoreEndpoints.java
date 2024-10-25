import Payloads.StorePayloads;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class StoreEndpoints extends BaseAPITest{

    @Test
    public void getStoreInventory() {
        String response = given()
                .header("accept", "application/json")
                .header("api_key", "qatest")
                .when().get("/store/inventory")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
        Assert.assertFalse(response.isEmpty());
    }

    @Test(priority = 1)
    public void placeAnOrderForAPet() throws IOException {
        Response response = given().header("Content-Type", "application/json")
                .body(StorePayloads.placeOrder(123, 3, "2024-05-01", "sold", true))
                .when().post("/store/order");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = ReusableMethods.convertStringToJSON(response.body().asPrettyString());
        String orderId = jsonPath.getString("id");
        String responseMsg = response.getStatusLine();
        ReusableMethods.createFileNoAppend("orderDetails.txt", orderId);

        Assert.assertTrue(statusCode == 200);
        Assert.assertTrue(responseMsg.contains("OK"));
    }

    @Test(priority = 1, dependsOnMethods = {"placeAnOrderForAPet"})
    public void findOrderByValidId() throws IOException {
        String orderId = ReusableMethods.readOrderDetailsFile();

        Response response = given().pathParam("orderId", orderId)
                .when().get("/store/order/{orderId}");
        int statusCode = response.getStatusCode();

        Assert.assertTrue(statusCode == 200);
    }

    @Test(priority = 2, dependsOnMethods = {"placeAnOrderForAPet", "findOrderByValidId()"})
    public void deleteOrderByValidId() throws IOException {
        String orderId = ReusableMethods.readOrderDetailsFile();
         Response response = given().pathParam("orderId", orderId)
                .when().delete("/store/order/{orderId}");

        int statusCode = response.getStatusCode();

        Assert.assertTrue(statusCode == 200);
    }

    //Per documentation it is supposed to return 400, not 404
    @Test
    public void deleteOrderByIncorrectdId() {
        Response response = given().pathParam("orderId", "-45")
                .when().delete("/store/order/{orderId}");

        int statusCode = response.getStatusCode();

        Assert.assertTrue(statusCode == 400 || statusCode == 404);
    }

}
