import java.io.File;
import java.util.Map;

public abstract class HTTPRequest extends HTTPObject {

    private final String resource;

    public HTTPRequest(Map<String, String> headers, byte[] body, String resource) {
        super(headers, body);
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public abstract HTTPResponse processRequest(File rootDirectory);
}
