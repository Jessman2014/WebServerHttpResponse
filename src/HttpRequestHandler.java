import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Set;

/**
 * @author Jesse Dahir-Kanehl
 * created - Feb 4th, 2015
 * 
 * Only has one method which is called by the web server
 */

public class HttpRequestHandler{
	
	/**
	 * 
	 * @param request
	 * @return
	 */
    public HttpResponse handleRequest(HttpRequest request) {	
    	HttpResponse hr = new CalcResponse();
    	StringBuilder body = new StringBuilder();
    	String color = "LightGrey";
    	BigInteger z = new BigInteger("1");
    	int x, y;
    	
    	if (request.getPath().equals("/api/mul") || request.getPath().equals("/api/pow")) {
    		if (request.getQuery("color") != null)
        		color = request.getQuery("color");
    		body.append("<html> <table bgcolor=\"" + color 
    				+ "\"> <tr> <th>Header name</th> <th>Description</th> </tr> ");
    		for (String key : request.getQueryNames()) {
				body.append("<tr> <td>" + key + "</td> <td>" + request.getHeader(key) + "</td> </tr>");
			}
    		body.append("</table> </html>");		
    		hr.setBody(body.toString());
    		if(request.getMethod().equals("POST")) {
    			request.X = 5;
    		}
    	}
    	else {
    		hr.setStatusCode("404");
    		hr.setDescription("Not Found");
    		hr.setVersion(request.getVersion());
    		hr.setHeader("Content-length", "0");
    		hr.setHeader("Content-type", "type/html");
    	}
    
    
    	return hr;
    }

	
}
