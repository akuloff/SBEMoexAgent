package SBEMoexAgent;

import SimpleIOSocket.ReadSocketProcess;
import SimpleIOSocket.SimpleClient;
import SimpleIOSocket.TwimeDecoder;
import org.agrona.concurrent.UnsafeBuffer;
import sbe.EstablishEncoder;
import sbe.MessageHeaderEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

/**
 * Created by mpoke_000 on 03.03.2017.
 */
public class ConsoleLauncher {

    public static String byteArrayToHex(byte[] a, int count) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        if (count <= a.length) {
            for (int i=0; i<count; i++)
                sb.append(String.format("%02x", a[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args){
        SimpleClient simpleClient;
        ReadSocketProcess readSocketProcess;
        WritableByteChannel outChannel;
        long intervalMsec = 10000L;
        TwimeDecoder twimeDecoder = new TwimeDecoder();

        simpleClient = new SimpleClient("91.208.232.244", 9000);
        simpleClient.doConnect();
        if (simpleClient.isConnected()){
            try {
                OutputStream outputStream = simpleClient.getSocket().getOutputStream();
                outChannel = Channels.newChannel(outputStream);

                twimeDecoder.setIntervalMsec(intervalMsec - 1000).setOutputChannel(outChannel);

                readSocketProcess = new ReadSocketProcess(simpleClient.getSocket()){
                    @Override
                    protected void processMessage(int actualReaded) {
                        super.processMessage(actualReaded);
                        //System.out.println("byte array, size: " + actualReaded + "\n" + byteArrayToHex(dataBuffer, actualReaded));
                        twimeDecoder.decodeMessage(unsafeBuffer);
                    }

                    @Override
                    protected void onStop() {
                        super.onStop();
                        if(twimeDecoder != null && twimeDecoder.getHeartBeatProcess() != null){
                            twimeDecoder.getHeartBeatProcess().setStopped(true);
                        }
                    }
                };

                Thread t = new Thread(readSocketProcess);
                t.start();


                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
                UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
                MessageHeaderEncoder MESSAGE_HEADER_ENCODER = new MessageHeaderEncoder();
                EstablishEncoder establishEncoder = new EstablishEncoder();
                int bufferOffset = 0;
                int encodingLength = 0;

                MESSAGE_HEADER_ENCODER
                        .wrap(directBuffer, bufferOffset)
                        .blockLength(establishEncoder.sbeBlockLength())
                        .templateId(establishEncoder.sbeTemplateId())
                        .schemaId(establishEncoder.sbeSchemaId())
                        .version(establishEncoder.sbeSchemaVersion());

                bufferOffset += MESSAGE_HEADER_ENCODER.encodedLength();
                encodingLength += MESSAGE_HEADER_ENCODER.encodedLength();

                establishEncoder.wrap(directBuffer, bufferOffset).credentials("twFZct_FZ00F36").keepaliveInterval(intervalMsec).timestamp(System.currentTimeMillis());
                encodingLength += establishEncoder.encodedLength();

                System.out.println("encodingLength: " + encodingLength);

                byteBuffer.limit(encodingLength);
                outChannel.write(byteBuffer);

                try {
                    Thread.sleep(60000);
                    System.out.println("end sleep ...");
                    if (twimeDecoder.getHeartBeatProcess() != null) {
                        twimeDecoder.getHeartBeatProcess().setStopped(true);
                    }
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                simpleClient.doDisconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
