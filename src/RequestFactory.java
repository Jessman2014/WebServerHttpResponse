/**
 * @author Jesse Dahir-Kanehl
 * created - Feb 4th, 2015
 * 
 * Only has one method which is static and called by the web server
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class RequestFactory implements HttpRequest{
		String method, version, path;
		Hashtable<String, String> queries = new Hashtable<>();
		Hashtable<String, String> headers = new Hashtable<>();
		
		@Override
		public void setMethod(String method) {
			this.method = method;
		}

		@Override
		public void setVersion(String version) {
			this.version = version;
		}

		@Override
		public void setPath(String path) {
			this.path = path;
		}

		@Override
		public void setQuery(String key, String value) {
			queries.put(key, value);
		}

		@Override
		public void setHeader(String key, String value) {
			headers.put(key, value);
		}

		@Override
		public String getMethod() {
			return method;
		}

		@Override
		public String getVersion() {
			return version;
		}

		@Override
		public String getPath() {
			return path;
		}

		@Override
		public String getHeader(String key) {
			return headers.get(key);
		}

		@Override
		public Set<String> getHeaderNames() {
			return headers.keySet();
		}

		@Override
		public String getQuery(String key) {
			return queries.get(key);
		}

		@Override
		public Set<String> getQueryNames() {
			return queries.keySet();
		}
	
	/**
	 * Reads lines from the buffered reader and splits them up into their respective parts
	 * of a http request
	 * @param bufferedReader - the http request as plain text
	 * @return httpRequest - the actual http request parsed from the text
	 * @throws IOException - if there is an error with reading the input it throws back
	 * 				to the web server
	 */
	public static HttpRequest createRequest(BufferedReader input) throws IOException {
    	HttpRequest hr = new RequestFactory();
    	String line = input.readLine();
    	//while (line != null) {
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
    		hr.setVersion(scan.next());
    		line = input.readLine();
    		//repeats for each header
    		while(!line.equals("")) {
    			scan = new Scanner(line);
    			String key = scan.next();
    			hr.setHeader(key, scan.next());
    			line = input.readLine();
    		}
    		/*line = input.readLine();
    		if (line != null) {
    			
    		}*/
    		scan.close();
    	//}
    	return hr;
    }
}
