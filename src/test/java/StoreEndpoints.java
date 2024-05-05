import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class StoreEndpoints extends BaseAPITest{

    @Test
    public void getStoreInventory() {
        String response = given()
                .when().get("/store/inventory")
                .then().log().all().assertThat().statusCode(200).extract().response().asPrettyString();
        Assert.assertFalse(response.isEmpty());
    }


}
