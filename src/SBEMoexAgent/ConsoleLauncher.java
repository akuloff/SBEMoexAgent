package SBEMoexAgent;

import SimpleIOSocket.ReadSocketProcess;
import SimpleIOSocket.SimpleSocketClient;
import SimpleIOSocket.TwimeClient;
import org.agrona.concurrent.UnsafeBuffer;
import sbe.EstablishEncoder;
import sbe.MessageHeaderEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
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
        SimpleSocketClient simpleSocketClient;
        ReadSocketProcess readSocketProcess;
        WritableByteChannel outChannel;
        long intervalMsec = 10000L;
        TwimeClient twimeClient = new TwimeClient();

        simpleSocketClient = new SimpleSocketClient("91.208.232.244", 9000);
        simpleSocketClient.doConnect();
        if (simpleSocketClient.isConnected()){
            try {
                OutputStream outputStream = simpleSocketClient.getSocket().getOutputStream();
                outChannel = Channels.newChannel(outputStream);

                twimeClient.setIntervalMsec(intervalMsec - 1000).setOutputChannel(outChannel);

                readSocketProcess = new ReadSocketProcess(simpleSocketClient.getSocket()){
                    @Override
                    protected void processMessage(int actualReaded) {
                        super.processMessage(actualReaded);
                        //System.out.println("byte array, size: " + actualReaded + "\n" + byteArrayToHex(dataBuffer, actualReaded));
                        twimeClient.decodeMessage(unsafeBuffer);
                    }

                    @Override
                    protected void onStop() {
                        super.onStop();
                        if(twimeClient != null && twimeClient.getHeartBeatProcess() != null){
                            twimeClient.getHeartBeatProcess().setStopped(true);
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

                String userName = System.getProperty("twimeUser");
                System.out.println("userName: " + userName);
                establishEncoder.wrap(directBuffer, bufferOffset).credentials(userName).keepaliveInterval(intervalMsec).timestamp(System.currentTimeMillis());
                encodingLength += establishEncoder.encodedLength();

                System.out.println("encodingLength: " + encodingLength);

                byteBuffer.limit(encodingLength);
                outChannel.write(byteBuffer);

                try {
                    Thread.sleep(60000);
                    System.out.println("end sleep ...");
                    if (twimeClient.getHeartBeatProcess() != null) {
                        twimeClient.getHeartBeatProcess().setStopped(true);
                    }
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                simpleSocketClient.doDisconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
