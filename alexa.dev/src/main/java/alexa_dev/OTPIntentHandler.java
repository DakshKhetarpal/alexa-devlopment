package alexa_dev;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;

public class OTPIntentHandler  implements RequestHandler {


	
    public boolean canHandle(HandlerInput input) {
       return input.matches(Predicates.intentName("OTPIntent"));
    }
    public Optional<Response> handle(HandlerInput input) {
   	 TockenFactory tockenFactory = new TockenFactory();    	
   	 Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        Slot OTPSlot = slots.get("OTP");
        String OTP = OTPSlot.getValue();
        String res= (String) input.getAttributesManager().getSessionAttributes().get("Res");
        String speechText = "OTP verified Hello";
   	 	try {        
   	 		User curr_user = tockenFactory.matchOTP(OTP,res);
   	 		speechText+= curr_user.getName()+", Your Current balance is "+ curr_user.getBalance();
   	 	}
   	 	catch (Exception e) {
   	 	 speechText = "OTP not verified please try again, Please Provide your Phone number";	
   	 	 }
   	 	
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(speechText, speechText)
                .withShouldEndSession(false)
                .build();
    }

}

