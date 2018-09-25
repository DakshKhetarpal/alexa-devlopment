package alexa_dev;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    public Optional<Response> handle(HandlerInput input) {
    	Map<String,Object> sessionAttributes = new HashMap<String,Object>();
    	sessionAttributes.put("Res", "value");

    	 input.getAttributesManager().setSessionAttributes(sessionAttributes);
        String speechText = "Welcome to Airtel Payments Bank, I can help you in your Basic Banking Features, Please login with your Phone number";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Welcome", speechText)
                .withShouldEndSession(false)
                .withReprompt(speechText)
                .build();
    }

    

}