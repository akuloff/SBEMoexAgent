package SimpleSocketTwimeClient;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by mpoke_000 on 07.03.2017.
 */
public class SimpleSocketClient {
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
            System.out.println("is connected: " + socket.isConnected() + " |time: " + new java.util.Date(System.currentTimeMillis()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doDisconnect(){
        if(socket != null){
            System.out.println("doDisconnect, is connected: " + socket.isConnected());
            if (socket.isConnected()) {
                try {
                    System.out.println("do close socket ...");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
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
