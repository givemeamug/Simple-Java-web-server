import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HTTPHeadRequest extends HTTPRequest {

    public HTTPHeadRequest(Map<String, String> headers, String resource) {
        super(headers, null, resource);
    }

    @Override
    public HTTPResponse processRequest(File rootDirectory) {
        HTTPResponseCode responseHeader;
        HashMap<String, String> headers = new HashMap<>();
        File file = new File(rootDirectory, getResource());

        if (file.exists()) {
            responseHeader = HTTPResponseCode.OK;
            HTTPUtil.fillHeadersWithContentInfo(headers, file);
        } else {
            responseHeader = HTTPResponseCode.NOT_FOUND;
        }
        return new HTTPHeadResponse(headers, responseHeader);
    }

    @Override
    public HTTPMethod getMethod() {
        return HTTPMethod.HEAD;
    }
}
