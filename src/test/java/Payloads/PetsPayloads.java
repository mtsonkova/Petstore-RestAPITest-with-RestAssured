package Payloads;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;

public class PetsPayloads {

    public static String addPetPayload(String categoryName, String petName, String[] url, String tag, String status) {
        // Create JSON object
        JsonObject pet = new JsonObject();

        // Add primitive fields
        pet.addProperty("id", 0);
        JsonObject category = new JsonObject();
        category.addProperty("id", 0);
        category.addProperty("name", categoryName);
        pet.add("category", category);

        pet.addProperty("name", petName);

        // Add array for photoUrls
        JsonArray photoUrls = new JsonArray();
        for (String u : url) {
            photoUrls.add(u);
        }
        pet.add("photoUrls", photoUrls);

        // Add tags as an array of objects
        JsonArray tags = new JsonArray();
        JsonObject tagObject = new JsonObject();
        tagObject.addProperty("id", 0);
        tagObject.addProperty("name", tag);
        tags.add(tagObject);
        pet.add("tags", tags);

        // Add status
        pet.addProperty("status", status);

        // Return the JSON string
        return pet.toString();
    }


    public static String addPetPayload2(String categoryName, String petName, String[] url, String tag, String status) {
        // Create JSON object
        JsonObject pet = new JsonObject();

        // Add primitive fields
        pet.addProperty("id", 0);
        JsonObject category = new JsonObject();
        category.addProperty("id", 0);
        category.addProperty("name", categoryName);
        pet.add("category", category);

        pet.addProperty("name", petName);
        // Add array for photoUrls
        JsonArray photoUrls = new JsonArray();
        for (String u : url) {
            photoUrls.add(u);
        }
        pet.add("photoUrls", photoUrls);
        // Add tags as an array of objects
        JsonArray tags = new JsonArray();
        JsonObject tagObject = new JsonObject();
        tagObject.addProperty("id", 0);
        tagObject.addProperty("name", tag);
        tags.add(tagObject);
        pet.add("tags", tags);

        // Add status
        pet.addProperty("status", status);

        // Return the JSON string
        return pet.toString();
    }
}
