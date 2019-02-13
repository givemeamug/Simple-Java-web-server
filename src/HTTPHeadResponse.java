import java.util.Map;

public class HTTPHeadResponse extends HTTPResponse {

    public HTTPHeadResponse(Map<String, String> headers, HTTPResponseCode responseCode) {
        super(headers, null, responseCode);
    }

    @Override
    public HTTPMethod getMethod() {
        return HTTPMethod.HEAD;
    }
}
