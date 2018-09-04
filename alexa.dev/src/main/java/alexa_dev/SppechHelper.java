package alexa_dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SppechHelper {
	
	@Autowired
	RestTemplate restTemplate;
	
static String text ;
	public  void  getQuote() {
		Quote quote = restTemplate.getForObject(
				"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		//log.warn(quote.toString());
		//System.out.println(quote.toString());
		text=quote.toString();
		System.out.println(text);
	}
}
