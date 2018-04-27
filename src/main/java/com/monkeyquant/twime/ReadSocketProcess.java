package com.monkeyquant.twime;

import org.agrona.concurrent.UnsafeBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mpoke_000 on 07.03.2017.
 */
public class ReadSocketProcess implements Runnable {
    private static final Logger logger = Logger.getLogger(ReadSocketProcess.class.getName());

    private InputStream inputStream = null;
    private boolean isStopped = false;
    protected final byte[] dataBuffer = new byte[4096];
    protected final UnsafeBuffer unsafeBuffer = new UnsafeBuffer(dataBuffer);

    public ReadSocketProcess(Socket socket, int readTimeoutMsec) {
        try {
            socket.setSoTimeout(readTimeoutMsec);
            inputStream = socket.getInputStream();
            logger.info("input stream initialized ...");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException: ", e);
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
                            logger.fine(String.format("..... ReadSocketProcess, actualReaded = %1$s", actualReaded));
                            processMessage(actualReaded);
                        } else {
                            logger.fine(String.format("..... ReadSocketProcess, actualReaded below zero: %1$s do stop, time: %2$s", actualReaded, java.sql.Timestamp.from(Instant.now())));
                            isStopped = true;
                            onStop();
                        }
                    } catch (SocketTimeoutException e) {
                        logger.log(Level.WARNING, "SocketTimeoutException: ", e);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    isStopped = true;
                    onStop();
                    logger.info(String.format("error read stream, do stop, time: %1$s", java.sql.Timestamp.from(Instant.now())));
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
