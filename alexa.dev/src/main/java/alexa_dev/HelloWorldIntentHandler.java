package alexa_dev;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;

import java.util.Map;
import java.util.Optional;


public class HelloWorldIntentHandler implements RequestHandler {


	
     public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("HelloWorldIntent"));
     }
     public Optional<Response> handle(HandlerInput input) {
    	 TockenFactory tockenFactory = new TockenFactory();    	
    	 Request request = input.getRequestEnvelope().getRequest();
         IntentRequest intentRequest = (IntentRequest) request;
         Intent intent = intentRequest.getIntent();
         Map<String, Slot> slots = intent.getSlots();

         
         Slot numberSlot = slots.get("number");
         String number = numberSlot.getValue();
    	 String res = tockenFactory.SendOTP(number);
    	 input.getAttributesManager().getSessionAttributes().put("Res", res);
    	String speechText = "OTP SEND with number  "+ number;
    	
         return input.getResponseBuilder()
                 .withSpeech(speechText)
                 .withSimpleCard(speechText, speechText)
                 .withShouldEndSession(false)
                 .build();
     }

}
