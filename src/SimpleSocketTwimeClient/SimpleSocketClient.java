package SimpleSocketTwimeClient;

import java.io.IOException;
import java.net.Socket;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mpoke_000 on 07.03.2017.
 */
public class SimpleSocketClient {
    private static Logger logger = Logger.getLogger(SimpleSocketClient.class.getName());

    private String hostAddress = null;
    private int port = 0;
    private Socket socket;

    public SimpleSocketClient(String hostAddress, int port) {
        this.hostAddress = hostAddress;
        this.port = port;
    }

    public boolean isConnected(){
        return socket != null && socket.isConnected();
    }

    public void doConnect(){
        try {
            socket = new Socket(hostAddress, port);
            logger.info(String.format("is connected: %1$s |time: %2$s", socket.isConnected(), java.sql.Timestamp.from(Instant.now())));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "InterruptedException: ", e);
        }
    }

    public void doDisconnect(){
        if(socket != null){
            logger.info(String.format("doDisconnect, is connected: %1$s", socket.isConnected()));
            if (socket.isConnected()) {
                try {
                    logger.info("do close socket ...");
                    socket.close();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "IOException: ", e);
                }
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public SimpleSocketClient setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
        return this;
    }

    public int getPort() {
        return port;
    }

    public SimpleSocketClient setPort(int port) {
        this.port = port;
        return this;
    }
}
