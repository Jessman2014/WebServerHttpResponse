import java.math.BigInteger;

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
    	
    	String color = request.getQuery("color");
    	BigInteger x, y, z;
    	
    	hr.setVersion(request.getVersion());
    	
    	if (request.getPath().equals("/api/mul") || request.getPath().equals("/api/pow")) {
    		body.append(" <table bgcolor=\"" + color 
    				+ "\"> <tr> <th>Header name</th> <th>Description</th> </tr> ");
    		for (String key : request.getHeaderNames()) {
				body.append("<tr> <td>" + key + "</td> <td>" + request.getHeader(key) + "</td> </tr>");
			}
    		body.append("</table> <div>");
    		if (request.getPath().equals("/api/mul")) {
    			if (request.getQuery("x") == null)
    				x = BigInteger.valueOf(StandardRequest.DEF_MUL_X);
    			else
    				x = new BigInteger(request.getQuery("x"));
    			if (request.getQuery("y") == null)
    				y = BigInteger.valueOf(StandardRequest.DEF_MUL_Y);
    			else
    				y = new BigInteger(request.getQuery("y"));
    			z = x.multiply(y);
    			body.append(x + " x " + y + " = " + z);
    		}
    		else {
    			if (request.getQuery("x") == null)
    				x = BigInteger.valueOf(StandardRequest.DEF_POW_X);
    			else
    				x = new BigInteger(request.getQuery("x"));
    			if (request.getQuery("y") == null)
    				y = BigInteger.valueOf(StandardRequest.DEF_POW_Y);
    			else
    				y = new BigInteger(request.getQuery("y"));
    			z = x.pow(y.intValue());
    			body.append(x + "<sup>" + y + "</sup> = " + z);
    		}
    		body.append("</div>");
    		hr.setBody(body.toString());
    		hr.setStatusCode("200");
    		hr.setDescription("OK");
    		hr.setHeader("Content-type", "type/html");
    		hr.setHeader("Content-length", "" + body.length());
    		if(request.getMethod().equals("POST")) {
    			StandardRequest.DEF_MUL_X = x.longValue();
    			StandardRequest.DEF_MUL_Y = y.longValue();
    			StandardRequest.DEF_POW_X = x.longValue();
    			StandardRequest.DEF_POW_Y = y.longValue();
    		}
    	}
    	else {
    		hr.setStatusCode("404");
    		hr.setDescription("Not Found");
    		hr.setHeader("Content-length", "0");
    		hr.setHeader("Content-type", "type/plain");
    	}
    	return hr;
    	
    }

	
}
