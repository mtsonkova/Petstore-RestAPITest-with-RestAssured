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
                .when().get("/store/inventory")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void placeAnOrderForAPet() throws IOException {
        Response response = given().header("Content-Type", "application/json")
                .body(StorePayloads.placeOrder(123, 3, "2024-05-01", "sold", true))
                .when().post("/store/order");

        int statusCode = response.getStatusCode();
        JsonPath jsonPath = ReusableMethods.convertStringToJSON(response.body().asPrettyString());
        String id = jsonPath.getString("id");

        String responseMsg = response.getStatusLine();
        ReusableMethods.createFileNoAppend("orderDetails.txt", id);
        Assert.assertTrue(statusCode == 200);
        Assert.assertTrue(responseMsg.contains("OK"));
    }

    @Test
    public void findOrderByValidId() {
        Response response = given().pathParam("orderId", "9223372036854775807")
                .when().get("/store/order/{orderId}");
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200);
    }

    @Test
    public void deleteOrderByValidId() {
        Response response = given().pathParam("orderId", "9223372036854775807")
                .when().delete("/store/order/{orderId}");

        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 404);
    }

    //Per documentation it is supposed to return 400, not 404
    @Test
    public void deleteOrderByIncorrectdId() {
        Response response = given().pathParam("orderId", "-5a")
                .when().delete("/store/order/{orderId}");

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertTrue(statusCode == 400);
    }

}
