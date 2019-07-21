package main.java.test;

import api.GeocodeApi;
import main.java.api.ZomataApi;
import org.json.JSONArray;
import org.json.JSONObject;
import util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    public static void main (String[] argc) {

        GeocodeApi geocodeApi = new GeocodeApi();
        String responseGeo = geocodeApi.zipCode("92128");
        JSONObject responseJsonGeo = new JsonUtil().parseJson(responseGeo);
        JSONObject location = responseJsonGeo.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

        Double lat = location.getDouble("lat");
        Double lon = location.getDouble("lng");

        ZomataApi zomataApi = new ZomataApi();
        String response = zomataApi.search(lat.toString(), lon.toString(), "1000");
        JSONObject responseJson = new JsonUtil().parseJson(response);
        JSONArray restaurants = responseJson.getJSONArray("restaurants");

        int[] randTracker = new int[5];
        int i = 0;
        List<JSONObject> chosenRestaurants = new ArrayList<JSONObject>();
        while (i < 5) {
            int randIndex = new Random().nextInt(restaurants.length());
            randTracker[i] = randIndex;

            boolean alreadyChosen = false;
            for (int j = 0; j < i; j++) {
                if (randIndex == randTracker[j]) alreadyChosen = true;
            }

            if (!alreadyChosen) {
                chosenRestaurants.add(restaurants.getJSONObject(randIndex));
                i++;
            }
        }

        JSONObject restaurant = chosenRestaurants.get(new Random().nextInt(5)).getJSONObject("restaurant");

        String name = restaurant.getString("name");
        String address = restaurant.getJSONObject("location").getString("address");

        String speechText = name + " at " + address;

    }

}
