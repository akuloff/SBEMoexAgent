package SimpleIOSocket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by mpoke_000 on 07.03.2017.
 */
public class ReadSocketProcess implements Runnable {
    private Socket socket = null;
    private InputStream inputStream = null;
    protected final byte[] dataBuffer = new byte[4096];
    private boolean isStopped = false;

    public ReadSocketProcess(Socket socket) {
        this.socket = socket;
        try {
            inputStream = socket.getInputStream();
            System.out.println("input stream initialized ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int actualReaded;
        if(inputStream != null){
            while (!isStopped){
                try {
                    actualReaded = inputStream.read(dataBuffer);
                    if (actualReaded >= 0) {
                        System.out.println("actualReaded = " + actualReaded);
                        processMessage(actualReaded);
                    } else {
                        System.out.println("actualReaded below zero: " + actualReaded + " do stop, time: " + new java.util.Date(System.currentTimeMillis()));
                        isStopped = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    isStopped = true;
                    System.out.println("error read stream, do stop, time: " + new java.util.Date(System.currentTimeMillis()));
                }
            }
        }
    }


    protected void processMessage(int actualReaded){

    }

    public boolean isStopped() {
        return isStopped;
    }

    public ReadSocketProcess setStopped(boolean stopped) {
        isStopped = stopped;
        return this;
    }
}
