package com.monkeyquant.twime;

import java.io.IOException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * процесс отправки heartbeat через TWIME
 */
public class TwimeHeartBeatProcess implements Runnable{
    private static final Logger logger = Logger.getLogger(TwimeHeartBeatProcess.class.getName());

    private boolean isStopped = false;
    private long intervalMsec = 0;
    private long lastSendTime = 0;

    private AbstractTwimeClient twimeClient;

    public TwimeHeartBeatProcess(AbstractTwimeClient twimeClient, long intervalMsec) {
        this.twimeClient = twimeClient;
        this.intervalMsec = intervalMsec;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public TwimeHeartBeatProcess setStopped(boolean stopped) {
        isStopped = stopped;
        return this;
    }

    private void sendSequence(long seqNum){
        try {
            logger.fine(" >> send sequence ...");
            twimeClient.sendHeartBeat();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException: ", e);
            this.setStopped(true);
        }
    }

    @Override
    public void run() {
        while(!isStopped){
            try {
                Thread.sleep(intervalMsec);
                if(!isStopped && needSendHeartbeat()) {
                    sendSequence(1);
                }
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "InterruptedException: ", e);
                isStopped = true;
            }
        }
    }

    private boolean needSendHeartbeat(){
        boolean result = true;
        if (lastSendTime > 0){
            if (java.sql.Timestamp.from(Instant.now()).getTime() - lastSendTime < intervalMsec) {
                result = false;
            }
        }
        return result;
    }

    public long getLastSendTime() {
        return lastSendTime;
    }

    public TwimeHeartBeatProcess setLastSendTime(long lastSendTime) {
        this.lastSendTime = lastSendTime;
        return this;
    }
}
