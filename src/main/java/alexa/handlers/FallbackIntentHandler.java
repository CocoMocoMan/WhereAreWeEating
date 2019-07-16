package main.java.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;


public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) { return input.matches(Predicates.intentName("AMAZON.FallbackIntent")); }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Sorry, I don't know that. Start over by saying Where are we eating or say help for more help!";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("There was a problem!", speechText)
                .withReprompt(speechText)
                .build();
    }
}

