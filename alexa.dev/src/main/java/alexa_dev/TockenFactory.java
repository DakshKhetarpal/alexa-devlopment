package alexa_dev;

import java.util.Map;

import org.springframework.boot.json.JsonSimpleJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
public class TockenFactory {
	 RestTemplate restTemplate = new RestTemplate();

	public TockenFactory() {	
	}
	public ResponseEntity<String> getReponce(String url,HttpHeaders httpHeaders,String requestJson){
    	 HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
    	 ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
    	 return response;
	}
	public String getNewToken(){
		HttpHeaders httpHeaders = new HttpHeaders();
		 httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    	 String url ="https://apptest.airtelbank.com/ecom/v2/initiateEcom.action";
    	 String requestJson ="{\"currency\":\"INR\",\"date\":\"27082018163555\",\"failUrl\":\"https://google.com\",\"successUrl\":\"https://google.com\",\"merchantId\":\"180704\",\"txnRefNO\":\"7330657493\",\"amount\":\"10\",\"endMid\":\"BD-123C\",\"merchantName\":\"www.zingoy.com\",\"feSessionId\":\"ECOMpR83xbhiqAc\"\n" + 
    	 		"}";
    	 ResponseEntity<String> response = getReponce(url, httpHeaders, requestJson);
          JsonSimpleJsonParser parser = new JsonSimpleJsonParser();
         Map<String,Object> reponseMap =  parser.parseMap(response.getBody());
        return  reponseMap.get("token").toString();
        }
	public  String SendOTP(String number){
		String token = getNewToken();
		System.out.println(number);
		String Encryptedrequest = Req.getEncryptedRequest(number);
		HttpHeaders httpHeaders = new HttpHeaders();
		 httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		 httpHeaders.add("x-access-token", token);
    	 String url ="https://apptest.airtelbank.com/ecom/v2/ecomSendOtp.action";
    	 String requestJson = Encryptedrequest;
    	 ResponseEntity<String> response = getReponce(url, httpHeaders, requestJson);
		return  response.getBody().toString()+"&TOKEN:"+token;
	}
	public String matchOTP(String OTP, String res) {
	
		String[] ok = res.split("&",2);
		String resPure = ok[0];
		JsonSimpleJsonParser parser = new JsonSimpleJsonParser();
        Map<String,Object> resMap =  parser.parseMap(resPure);
        String verificationToken =  resMap.get("verificationToken").toString();
		//String verificationToken = ok[0];
		String token = ok[1].substring(6);
		System.out.println(token);
		System.out.println(verificationToken);
		HttpHeaders httpHeaders = new HttpHeaders();
		 httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		 httpHeaders.add("x-access-token", token);
		 String url ="https://apptest.airtelbank.com/ecom/v2/ecomVerifyOtp.action";
		 String requestJson  = "{\"otp\":\""+OTP+"\",\"verificationToken\":\""+verificationToken+"\",\"channel\":\"WEB\",\"feSessionId\":\"ECOMvDgPpJ3fWn4\"}";
		 ResponseEntity<String> response = getReponce(url, httpHeaders, requestJson);
		 String responceJson = response.getBody().toString();
	        Map<String,Object> reponseMap =  parser.parseMap(responceJson);
	        String profileInfo =  reponseMap.get("profileInfo").toString();
	        Map<String,Object> ProfileMap =  parser.parseMap(profileInfo);
	       String CUST_FIRST_NAME = ProfileMap.get("CUST_FIRST_NAME").toString();
		 return CUST_FIRST_NAME;
		 
	}
	
}
