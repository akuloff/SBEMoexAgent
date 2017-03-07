package SimpleIOSocket;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by mpoke_000 on 07.03.2017.
 */
public class SimpleClient {
    private String serverAddr = null;
    private int serverPort = 0;
    private Socket socket;

    public SimpleClient(String serverAddr, int serverPort) {
        this.serverAddr = serverAddr;
        this.serverPort = serverPort;
    }

    public boolean isConnected(){
        return socket != null && socket.isConnected();
    }

    public void doConnect(){
        try {
            socket = new Socket(serverAddr, serverPort);
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
}
