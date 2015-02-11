/**
 * @author Jesse Dahir-Kanehl
 * created - Feb 4th, 2015
 * 
 * Only has one method which is static and called by the web server
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class RequestFactory{
	
	/**
	 * Reads lines from the buffered reader and splits them up into their respective parts
	 * of a http request
	 * @param bufferedReader - the http request as plain text
	 * @return httpRequest - the actual http request parsed from the text
	 * @throws IOException - if there is an error with reading the input it throws back
	 * 				to the web server
	 */
	public static HttpRequest createRequest(BufferedReader input) throws IOException {
    	HttpRequest hr = new StandardRequest();
    	String line = input.readLine();
    	Scanner scan = new Scanner(line);
    	hr.setMethod(scan.next());
    	String url = scan.next();
    	String[] urlParts = url.split("[?]");
    	hr.setPath(urlParts[0]);
    	//sometimes their isn't a query which will skip this if
    	if (urlParts.length > 1) {
    		Scanner sc = new Scanner(urlParts[1]);
    		sc.useDelimiter("&");
    		while(sc.hasNext()) {
    			String[] query = sc.next().split("[=]");
        		hr.setQuery(query[0], query[1]);
    		}
    		sc.close();
    	}
    	String[] version = scan.next().split("[/]");
    	hr.setVersion(version[0] + ":/" + version[1]);
    	line = input.readLine();
    	//repeats for each header
    	while(!line.equals("")) {
    		scan = new Scanner(line);
    		String key = scan.next();
    		key = key.substring(0, key.length()-1);
    		hr.setHeader(key, scan.next());
    		line = input.readLine();
    	}
    	scan.close();
    	return hr;
    }
}
