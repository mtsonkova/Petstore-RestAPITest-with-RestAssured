package Payloads;

import com.google.gson.JsonObject;

public class StorePayloads {

    public static String placeOrder(int petID, int qty, String shipDate, String status, Boolean complete) {
        /*
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"petId\":" + petID + ",\n" +
                "  \"quantity\": " + qty + ",\n" +
                "  \"shipDate\": \"" + shipDate + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"complete\":" + complete + "\n" +
                "}";

         */

        JsonObject order = new JsonObject();
        order.addProperty("id",0);
        order.addProperty("petId", petID);
        order.addProperty("quantity", qty);
        order.addProperty("shipDate", shipDate);
        order.addProperty("status", status);
        order.addProperty("complete", complete);

        return order.toString();
    }
}
