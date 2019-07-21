package main.java.alexa.handlers;

import api.GeocodeApi;
import com.amazon.ask.request.RequestHelper;
import main.java.api.ZomataApi;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import org.json.JSONArray;
import org.json.JSONObject;
import util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RestaurantIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("RestaurantIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        RequestHelper requestHelper = RequestHelper.forHandlerInput(input);
        Optional<String> locationZip = requestHelper.getSlotValue("Location");
        Optional<String> distanceMiles = requestHelper.getSlotValue("Distance");

        System.out.println("zip code: " + locationZip.get());
        GeocodeApi geocodeApi = new GeocodeApi();
        String responseGeo = geocodeApi.zipCode(locationZip.get());
        JSONObject responseJsonGeo = new JsonUtil().parseJson(responseGeo);
        JSONObject location = responseJsonGeo.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

        Double lat = location.getDouble("lat");
        Double lon = location.getDouble("lng");

        ZomataApi zomataApi = new ZomataApi();
        String responseZom = zomataApi.search(lat.toString(), lon.toString(), "1000");
        JSONObject responseJsonZom = new JsonUtil().parseJson(responseZom);
        JSONArray restaurants = responseJsonZom.getJSONArray("restaurants");

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

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Your Restaurant", speechText)
                .build();

    }

}
