package alexa_dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String args[]) {
   	 TockenFactory tockenFactory = new TockenFactory();    	
	 String res = tockenFactory.SendOTP("9991310365");
	 System.out.println(res);
	 String out = tockenFactory.matchOTP("310365",res);
	 System.out.println(out);
	 
		SpringApplication.run(Application.class);
	}
}