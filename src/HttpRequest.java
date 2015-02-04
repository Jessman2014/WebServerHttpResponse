import java.util.Set;

public interface HttpRequest {
	// Setters
    public void setMethod(String method);
    public void setVersion(String version);
    public void setPath(String path);
    public void setQuery(String key, String value);
    public void setHeader(String key, String value);
    
    // Getters
    public String getMethod();
    public String getVersion();
    public String getPath();
    
    public String getHeader(String key);
    public Set<String> getHeaderNames();
    
    public String getQuery(String key);
    public Set<String> getQueryNames();
}