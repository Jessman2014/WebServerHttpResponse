import java.util.Hashtable;
import java.util.Set;

/**
 * @author Jesse Dahir-Kanehl
 * created - Feb 4th, 2015
 */
public class StandardRequest implements HttpRequest {
	String method, version, path;
	Hashtable<String, String> queries = new Hashtable<>();
	Hashtable<String, String> headers = new Hashtable<>();
	private static final String DEF_COLOR = "LightGrey";
	public static long DEF_MUL_X = 1, DEF_MUL_Y = 1, DEF_POW_X = 2, DEF_POW_Y = 0;
	
	public StandardRequest() {
		queries.put("color", DEF_COLOR);
	}
	
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

}
