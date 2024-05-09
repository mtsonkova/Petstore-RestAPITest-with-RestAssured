package Payloads;

public class UsersPayload {
    public static String createNewUser(String userName, String firstName,
                                       String lastName, String email, String password,
                                       String phone, int userStatus) {
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"username\": \"" + userName + "\",\n" +
                "  \"firstName\": \"" + firstName + "\",\n" +
                "  \"lastName\": \"" + lastName + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"phone\": \"" + phone + "\",\n" +
                "  \"userStatus\": " + userStatus + "\n" +
                "}";
    }
}
