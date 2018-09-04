package alexa_dev;



import org.apache.commons.codec.binary.Base64;

import com.airtel.encryption.*;

public class Req {
public static String getEncryptedRequest(String number){
		String data=" {\"feSessionId\":\"ECOMpR83xbhiqAc\",\"customerId\":\""+number+"\"}";
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnsUrIe1pCJLoggVpXwd3GWeoX+Fc6uAYUkPrgrewiXh8995uTPVKzfJAy5ioqFmOfpXKIuaFGd4wS/BraLlHxtM+/qvAkreDKmLaBYqn0p3EaEwKUIPXx4LATA9Uip3WZMmZ08ToHwJY8pCkDqWLyHJt45f6j2WLPwom45e+bO7UvUv1lhSxWCNuD8KbwPrhwKUOiY5PL+PSMgSyMnTVOMQICF5X8JSRFzTdwbNls/LFrDqBOxjj26QvL2oo9IYWsClrfupILFl5M6exxY2QATqF8En/LnD4eiow1I4Wt8scYmvplS22Upbwartf/9MB0gufXriA2vEl+QaB+j46OQIDAQAB";
		byte[] encryptedData = Base64.decodeBase64(publicKey);
		String Data=" ";
		try {
			Data = new EncryptionRSA().encrypt(data, encryptedData );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Data;
	}
}
