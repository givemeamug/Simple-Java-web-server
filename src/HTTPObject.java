import java.util.Map;

public abstract class HTTPObject {

    private final Map<String, String> headers;
    private final byte[] body;

    public HTTPObject(Map<String, String> headers, byte[] body) {
        this.headers = headers;
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    public abstract HTTPMethod getMethod();

    public byte[] getBody() {
        return body;
    }
}
