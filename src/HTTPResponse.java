import java.util.Map;

public abstract class HTTPResponse extends HTTPObject {

    private final HTTPResponseCode responseCode;

    public HTTPResponse(Map<String, String> headers, byte[] body, HTTPResponseCode responseCode) {
        super(headers, body);
        this.responseCode = responseCode;
    }

    public HTTPResponseCode getResponseCode() {
        return responseCode;
    }

}
