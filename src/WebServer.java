import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {

    private final File rootDirectory;
    private final int port;
    private final ExecutorService connectionHandlerExecutor;
    private ServerSocket serverSocket;

    public WebServer(File rootDirectory, int port) {
        this.rootDirectory = rootDirectory;
        this.port = port;
        connectionHandlerExecutor = Executors.newFixedThreadPool(50);
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started... listening on port " + port + " with root directory: " + rootDirectory);
            while(true){
                Socket conn = serverSocket.accept();
                System.out.println("Server got new connection request from " + conn.getInetAddress());
                WebServerConnectionHandler connectionHandler = new WebServerConnectionHandler(conn, rootDirectory);
                connectionHandlerExecutor.execute(connectionHandler);
            }
        } catch (IOException ioe){
            System.out.println("Ooops " + ioe.getMessage());
        }
    }
}
