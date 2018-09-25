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
         System.out.println(numberSlot);
         String number = numberSlot.getValue();
         System.out.println(number);
    	 String res = tockenFactory.SendOTP(number);
    	 System.out.println(res);
    	 input.getAttributesManager().getSessionAttributes().put("Res", res);
    	 input.getAttributesManager().getSessionAttributes().put("number", number);
     	number = number.replace("", " ").trim();
    	 String speechText = "OTP SEND with number, "+ number;
    	 
         return input.getResponseBuilder()
                 .withSpeech(speechText)
                 .withSimpleCard(speechText, speechText)
                 .withShouldEndSession(false)
                 .build();
     }

}
