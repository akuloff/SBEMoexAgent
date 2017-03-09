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

        System.out.println("hello, World! " + new java.util.Date(System.currentTimeMillis()));

        SimpleClient simpleClient;
        ReadSocketProcess readSocketProcess;

        TwimeDecoder twimeDecoder = new TwimeDecoder();

        simpleClient = new SimpleClient("91.208.232.244", 9000);
        simpleClient.doConnect();

        readSocketProcess = new ReadSocketProcess(simpleClient.getSocket()){
            @Override
            protected void processMessage(int actualReaded) {
                super.processMessage(actualReaded);
                System.out.println("byte array, size: " + actualReaded + "\n" + byteArrayToHex(dataBuffer, actualReaded));
                twimeDecoder.decodeMessage(unsafeBuffer);
            }
        };

        Thread t = new Thread(readSocketProcess);
        t.start();

        //тестовая отправка сообщения
        if (simpleClient.isConnected()){
            try {
                OutputStream outputStream = simpleClient.getSocket().getOutputStream();

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

                establishEncoder.wrap(directBuffer, bufferOffset).credentials("twFZct_FZ00F36").keepaliveInterval(5000).timestamp(System.currentTimeMillis());
                encodingLength += establishEncoder.encodedLength();

                System.out.println("encodingLength: " + encodingLength);

                byteBuffer.limit(encodingLength);
                byte[] arr = new byte[byteBuffer.remaining()];
                byteBuffer.get(arr);
                outputStream.write(arr);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simpleClient.doDisconnect();

    }

}
