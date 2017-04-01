package SBEMoexAgent;

import SimpleSocketTwimeClient.AbstractTwimeClient;
import SimpleSocketTwimeClient.MyTwimeClient;
import SimpleSocketTwimeClient.ReadSocketProcess;
import SimpleSocketTwimeClient.SimpleSocketClient;
import sbe.SideEnum;
import sbe.TimeInForceEnum;

import java.io.IOException;
import java.io.OutputStream;
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
        MyTwimeClient myTwimeClient = new MyTwimeClient();

        /*
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
        UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
        Decimal5Encoder decimal5Encoder = new Decimal5Encoder();
        decimal5Encoder.wrap(directBuffer, 0);
        decimal5Encoder.mantissa(106000);
        System.out.println(decimal5Encoder.toString());
        */

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
                        myTwimeClient.decodeMessage(unsafeBuffer);
                    }

                    @Override
                    protected void onStop() {
                        super.onStop();
                        myTwimeClient.stopHeartBeatProcess();
                    }
                };

                Thread t = new Thread(readSocketProcess);
                t.start();

                String userName = System.getProperty("twimeUser");
                String userAccount = System.getProperty("userAccount");
                System.out.println("userName: " + userName + " |userAccount: " + userAccount);

                myTwimeClient.setIntervalMsec(intervalMsec - 1000).setOutputChannel(outChannel);
                myTwimeClient.setUserAccount(userAccount);
                myTwimeClient.sendEstablish(userName);

                try {
                    //период работы клиента
                    Thread.sleep(10000);

                    //System.out.println("send mass cancel ...");
                    //abstractTwimeClient.sendOrderMassCancelRequest(0, 0, 1, SecurityTypeEnum.Future, SideEnum.AllOrders, "");

                    //System.out.println("send new order ...");
                    //myTwimeClient.sendNewOrderSingle(System.currentTimeMillis(), 111000, 1L, 398210, 0, TimeInForceEnum.Day, SideEnum.Sell);

                    Thread.sleep(10000);

                    System.out.println("end sleep ...");
                    myTwimeClient.stopHeartBeatProcess();

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
