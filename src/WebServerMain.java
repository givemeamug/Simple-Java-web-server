import java.io.File;

public class WebServerMain {

    public static void main(String[] args) {
        String usageMessage = "Usage: java WebServerMain <document_root> <port>";
        if (args == null || args.length != 2) {
            System.out.println(usageMessage);
            return;
        }
        String rootPath = args[0];
        File rootDirectory = new File(rootPath);
        int port = 0;
        String error = "";
        if (!rootDirectory.exists()) {
            error += "Root directory doesn't exist" + System.lineSeparator();
        }
        if (!rootDirectory.isDirectory()) {
            error += "Root directory is not a directory" + System.lineSeparator();
        }
        try {
            port = Integer.parseInt(args[1]);
            if (port < 0 || port > 65535) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            error += "Invalid port";
        }
        if (!error.isEmpty()) {
            System.out.println(error);
            return;
        }
        WebServer webServer = new WebServer(rootDirectory, port);
        webServer.start();
    }
}
