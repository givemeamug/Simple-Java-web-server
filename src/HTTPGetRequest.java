import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class HTTPGetRequest extends HTTPRequest {

    public HTTPGetRequest(Map<String, String> headers, String resource, byte[] body) {
        super(headers, body, resource);
    }

    @Override
    public HTTPResponse processRequest(File rootDirectory) {
        HTTPResponseCode responseCode;
        HashMap<String, String> headers = new HashMap<>();
        byte[] bodyResponse = null;
        File file = new File(rootDirectory, getResource());
        if (file.exists()) {
            responseCode = HTTPResponseCode.OK;
            try {
                bodyResponse = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                responseCode = HTTPResponseCode.INTERNAL_SERVER_ERROR;
            }
        } else {
            responseCode = HTTPResponseCode.NOT_FOUND;
            try {
                file = new File(rootDirectory, "/404page.html");
                bodyResponse = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                bodyResponse = "404 Not Found".getBytes();
            }
        }
        HTTPUtil.fillHeadersWithContentInfo(headers, file);
        return new HTTPGetResponse(headers, bodyResponse, responseCode);
    }

    @Override
    public HTTPMethod getMethod() {
        return HTTPMethod.GET;
    }
}
