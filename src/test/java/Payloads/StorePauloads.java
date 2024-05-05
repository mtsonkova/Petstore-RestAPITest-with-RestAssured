package Payloads;

public class StorePauloads {

    public static String placeOrder(int petID, int qty, String shipDate, String status, Boolean complete) {
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"petId\":" + petID + ",\n" +
                "  \"quantity\": " + qty + ",\n" +
                "  \"shipDate\": \"" + shipDate + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"complete\":" + complete + "\n" +
                "}";
    }
}
