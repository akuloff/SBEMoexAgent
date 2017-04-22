package SimpleSocketTwimeClient;

import org.agrona.concurrent.UnsafeBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by mpoke_000 on 07.03.2017.
 */
public class ReadSocketProcess implements Runnable {
    private Socket socket = null;
    private InputStream inputStream = null;
    private boolean isStopped = false;
    protected final byte[] dataBuffer = new byte[4096];
    protected final UnsafeBuffer unsafeBuffer = new UnsafeBuffer(dataBuffer);

    public ReadSocketProcess(Socket socket, int readTimeoutMsec) {
        this.socket = socket;
        try {
            socket.setSoTimeout(readTimeoutMsec);
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
            while (!isStopped) {
                try {
                    try {
                        actualReaded = inputStream.read(dataBuffer);
                        if (actualReaded >= 0) {
                            System.out.println("..... ReadSocketProcess, actualReaded = " + actualReaded);
                            processMessage(actualReaded);
                        } else {
                            System.out.println("..... ReadSocketProcess, actualReaded below zero: " + actualReaded + " do stop, time: " + new java.util.Date(System.currentTimeMillis()));
                            isStopped = true;
                            onStop();
                        }
                    } catch (SocketTimeoutException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    isStopped = true;
                    onStop();
                    System.out.println("error read stream, do stop, time: " + new java.util.Date(System.currentTimeMillis()));
                }
            }
        }
    }


    protected void processMessage(int actualReaded){}

    protected void onStop(){}

    public boolean isStopped() {
        return isStopped;
    }

    public ReadSocketProcess setStopped(boolean stopped) {
        isStopped = stopped;
        return this;
    }
}
