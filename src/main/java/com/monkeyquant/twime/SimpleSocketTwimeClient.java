package com.monkeyquant.twime;

import generated.sbe.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleSocketTwimeClient extends AbstractTwimeClient {
    private static final Logger logger = Logger.getLogger(SimpleSocketTwimeClient.class.getName());

    private TwimeHeartBeatProcess heartBeatProcess = null;
    private long heartBeatInterval = 0;

    private String hostAddress = null;
    private int connectionPort;
    private int socketReadTimeoutMsec = 10000;

    private ReadSocketProcess readSocketProcess = null;
    private WritableByteChannel outputChannel = null;

    public long getHeartBeatInterval() {
        return heartBeatInterval;
    }

    public SimpleSocketTwimeClient setHeartBeatInterval(long heartBeatInterval) {
        this.heartBeatInterval = heartBeatInterval;
        return this;
    }

    private void stopHeartBeatProcess(){
        if (heartBeatProcess != null && !heartBeatProcess.isStopped()) {
            heartBeatProcess.setStopped(true);
        }
    }

    public void stopConnectionProcess(){
        if (readSocketProcess != null && !readSocketProcess.isStopped()){
            readSocketProcess.setStopped(true);
        }
    }


    @Override
    protected void onEstablishmentAck(EstablishmentAckDecoder decoder) {
        if (heartBeatProcess == null) {
            heartBeatProcess = new TwimeHeartBeatProcess(this, heartBeatInterval);
        }
        new Thread(heartBeatProcess).start();
    }

    public void startProcess(){
        SimpleSocketConnector simpleSocketClient = new SimpleSocketConnector(this.hostAddress, this.connectionPort);
        simpleSocketClient.doConnect();
        if (simpleSocketClient.isConnected()){
            try {
                outputChannel = Channels.newChannel(simpleSocketClient.getSocket().getOutputStream());
                readSocketProcess = new ReadSocketProcess(simpleSocketClient.getSocket(), socketReadTimeoutMsec){
                    @Override
                    protected void processMessage(int actualReaded) {
                        int currentOffset = 0;
                        while(currentOffset < actualReaded) {
                            currentOffset = SimpleSocketTwimeClient.this.decodeMessage(unsafeBuffer, currentOffset);
                        }
                    }

                    @Override
                    protected void onStop() {
                        SimpleSocketTwimeClient.this.stopHeartBeatProcess();
                    }
                };

                Thread connectionThread = new Thread(readSocketProcess);
                connectionThread.start();

                this.sendEstablish();
                try {
                    connectionThread.join();
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, "InterruptedException: ", e);
                }

                SimpleSocketTwimeClient.this.stopHeartBeatProcess();
                simpleSocketClient.doDisconnect();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "IOException: ", e);
            }
        }
    }

    @Override
    protected synchronized void sendBuffer(ByteBuffer buffer, int length) throws IOException {
        buffer.limit(length);
        outputChannel.write(buffer);
        if (heartBeatProcess != null) {
            heartBeatProcess.setLastSendTime(java.sql.Timestamp.from(Instant.now()).getTime());
        }
    }

    @Override
    protected void onTerminate(TerminateDecoder decoder) {

    }

    @Override
    protected void onOrderMassCancelResponse(OrderMassCancelResponseDecoder decoder) {

    }

    @Override
    protected void onSessionReject(SessionRejectDecoder decoder) {

    }

    @Override
    protected void onEstablishmentReject(EstablishmentRejectDecoder decoder) {

    }

    @Override
    protected void onSequence(SequenceDecoder decoder) {

    }

    @Override
    protected void onRetransmission(RetransmissionDecoder decoder) {

    }

    @Override
    protected void onFloodReject(FloodRejectDecoder decoder) {

    }

    public String getHostAddress() {
        return hostAddress;
    }

    public SimpleSocketTwimeClient setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
        return this;
    }

    public int getConnectionPort() {
        return connectionPort;
    }

    public SimpleSocketTwimeClient setConnectionPort(int connectionPort) {
        this.connectionPort = connectionPort;
        return this;
    }

    public int getSocketReadTimeoutMsec() {
        return socketReadTimeoutMsec;
    }

    public SimpleSocketTwimeClient setSocketReadTimeoutMsec(int socketReadTimeoutMsec) {
        this.socketReadTimeoutMsec = socketReadTimeoutMsec;
        return this;
    }
}
