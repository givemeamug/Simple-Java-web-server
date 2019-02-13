import java.util.Map;

public enum HTTPMethod {
    GET {
        @Override
        public HTTPRequest makeRequest(Map<String, String> headers, String resource, byte[] body) {
            return new HTTPGetRequest(headers, resource, body);
        }
    },
    HEAD {
        @Override
        public HTTPRequest makeRequest(Map<String, String> headers, String resource, byte[] body) {
            return new HTTPHeadRequest(headers, resource);
        }
    };

    public abstract HTTPRequest makeRequest(Map<String, String> headers, String resource, byte[] body);
}
