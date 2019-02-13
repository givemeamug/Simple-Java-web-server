import java.util.Map;

public class HTTPGetResponse extends HTTPResponse {

    public HTTPGetResponse(Map<String, String> headers, byte[] body, HTTPResponseCode responseCode) {
        super(headers, body, responseCode);
    }

    @Override
    public HTTPMethod getMethod() {
        return HTTPMethod.GET;
    }

}
