package com.uns.ftn.sciencejournal.service.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.uns.ftn.sciencejournal.model.PaperSearchModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class GoogleCoordinatesService {

    private static String GOOGLE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static String GOOGLE_API_KEY = "AIzaSyChqtYEEgRO0gPZJRwkKCnT8Ew-BRMgXPQ";
    private static RestTemplate template = new RestTemplate();

    public PaperSearchModel.Location getCoordinatesFromAddress(String city, String country) {
        try {
            String uri = GOOGLE_API_URL + "?address=" +
                    URLEncoder.encode(city, "UTF-8") + "," + URLEncoder.encode(country, "UTF-8") +
                    "&key=" + GOOGLE_API_KEY;

            ResponseEntity<String> jsonResponse = template.getForEntity(new URI(uri), String.class);
            Map<String, String> coordinates = extractCoordinatesFromJson(jsonResponse.getBody());
            PaperSearchModel model = new PaperSearchModel();
            return model.new Location(coordinates.get("lat"), coordinates.get("lng"));
        } catch (UnsupportedEncodingException | URISyntaxException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Map<String, String> extractCoordinatesFromJson(String json) {
        Gson gson = new Gson();

        JsonObject location = gson.fromJson(json, JsonObject.class)
                .get("results").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("geometry").getAsJsonObject()
                .get("location").getAsJsonObject();

        String latitude = location.get("lat").getAsString();
        String longitude = location.get("lng").getAsString();

        Map<String, String> coordinates = new HashMap<>();
        coordinates.put("lat", latitude);
        coordinates.put("lng", longitude);
        return coordinates;
    }
}
