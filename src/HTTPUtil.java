import java.io.File;
import java.net.URLConnection;
import java.util.Map;

public class HTTPUtil {

    private HTTPUtil() {
    }

    public static void fillHeadersWithContentInfo(Map<String, String> headers, File file) {
        String contentType = URLConnection.guessContentTypeFromName(file.getName());
        long length = file.length();
        headers.put("Content-Type", contentType);
        headers.put("Content-Length", length + "");
    }

}
