package alexa.handlers;

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
        String speechText = "Got it!";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Got it!", speechText)
                .build();
    }

}
