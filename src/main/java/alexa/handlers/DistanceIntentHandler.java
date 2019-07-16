package main.java.alexa.handlers;

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

public class DistanceIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("DistanceIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        ZomataApi zomataApi = new ZomataApi();
        String response = zomataApi.search("32.962822", "-117.035866", "1000");
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

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Your Restaurant", speechText)
                .build();

    }

}
