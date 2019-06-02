package alexa.handlers;

import api.ZomataApi;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class DistanceIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("DistanceIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        ZomataApi zomataApi = new ZomataApi();
        String response = zomataApi.search(1, 1, 1);

        String speechText = response;

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(response, speechText)
                .build();
    }

}
