package SBEMoexAgent;

import SimpleIOSocket.ReadSocketProcess;
import SimpleIOSocket.SimpleSocketClient;
import SimpleIOSocket.TwimeClient;
import org.agrona.concurrent.UnsafeBuffer;
import sbe.EstablishEncoder;
import sbe.MessageHeaderEncoder;
import sbe.SecurityTypeEnum;
import sbe.SideEnum;

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

                String userName = System.getProperty("twimeUser");
                String userAccount = System.getProperty("userAccount");
                System.out.println("userName: " + userName + " |userAccount: " + userAccount);

                twimeClient.setIntervalMsec(intervalMsec - 1000).setOutputChannel(outChannel);
                twimeClient.setUserAccount(userAccount);
                twimeClient.sendEstablish(userName);

                try {
                    //период работы

                    Thread.sleep(20000);

                    System.out.println("send mass cancel ...");
                    twimeClient.sendOrderMassCancelRequest(0, 0, 1, SecurityTypeEnum.Future, SideEnum.AllOrders, "");

                    Thread.sleep(5000);

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
