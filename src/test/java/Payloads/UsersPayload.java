package Payloads;

import com.google.gson.JsonObject;

public class UsersPayload {
    public static String createNewUser(String userName, String firstName,
                                       String lastName, String email, String password,
                                       String phone, int userStatus) {

        JsonObject user = new JsonObject();

        user.addProperty("id", 0);
        user.addProperty("username", userName);
        user.addProperty("firstName", firstName);
        user.addProperty("lastName", lastName);
        user.addProperty("email", email);
        user.addProperty("password", password);
        user.addProperty("phone", phone);
        user.addProperty("userStatus", userStatus);

        return  user.toString();
    }
}
