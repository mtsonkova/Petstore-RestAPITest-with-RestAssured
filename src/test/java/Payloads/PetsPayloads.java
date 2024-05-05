package Payloads;

import java.util.ArrayList;
import java.util.Arrays;

public class PetsPayloads {

    public static String addPetPayload(String categoryName, String petName, String[] url, String tag, String status) {
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"" + categoryName + "\"\n" +
                "  },\n" +
                "  \"name\": \"" + petName + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"" + (Arrays.toString(url)) + "\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"" + tag + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
    }

    public static String addPetPayload2(String categoryName, String petName, String url, String tag, String status) {
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"" + categoryName + "\"\n" +
                "  },\n" +
                "  \"name\": \"" + petName + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"" + url + "\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"" + tag + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
    }




}
