import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class WebServerConnectionHandler implements Runnable {

    private final Socket clientConnection;
    private final File rootDirectory;

    public WebServerConnectionHandler(Socket clientConnection, File rootDirectory) throws IOException {
        this.clientConnection = clientConnection;
        this.rootDirectory = rootDirectory;
    }

    @Override
    public void run() {
        try (InputStream inputStream = clientConnection.getInputStream();
             OutputStream outputStream = clientConnection.getOutputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        ) {
            String firstLine = bufferedReader.readLine(); // With current functionality we need to read only the first line
            String[] split = firstLine.split(" ");
            String method = split[0];
            String resource = split[1];
            String version = split[2];

            HTTPResponseCode responseCode = null;
            if (version.equals("HTTP/1.1")) {
                try {
                    HTTPMethod httpMethod = HTTPMethod.valueOf(method);
                    HTTPRequest request = httpMethod.makeRequest(new HashMap<>(), resource, null);
                    HTTPResponse httpResponse = request.processRequest(rootDirectory);
                    writeResponse(httpResponse, dataOutputStream);
                } catch (IllegalArgumentException e) {
                    responseCode = HTTPResponseCode.NOT_IMPLEMENTED;
                }
            } else {
                responseCode = HTTPResponseCode.HTTP_VERSION_NOT_SUPPORTED;
            }
            if (responseCode != null) {
                dataOutputStream.write(("HTTP/1.1 " + responseCode + "\r\n\r\n").getBytes());
            }
            dataOutputStream.flush();
            clientConnection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeResponse(HTTPResponse response, OutputStream outputStream) throws IOException {
        String statusLine = "HTTP/1.1 " + response.getResponseCode().toString() + "\r\n";
        outputStream.write(statusLine.getBytes());
        for (Map.Entry<String, String> headerEntry : response.getHeaders().entrySet()) {
            String header = headerEntry.getKey() + ": " + headerEntry.getValue() + "\r\n";
            outputStream.write(header.getBytes());
        }
        outputStream.write("\r\n".getBytes());
        outputStream.write(response.getBody());
    }

}
