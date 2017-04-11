package SimpleSocketTwimeClient;

import org.agrona.concurrent.UnsafeBuffer;
import sbe.*;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

/**
 * Created by mpoke_000 on 08.03.2017.
 */
public class AbstractTwimeClient implements Runnable{
    private MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
    private long sequenceNum = 0;
    private TwimeHeartBeatProcess heartBeatProcess = null;
    private long intervalMsec = 0;
    private WritableByteChannel outputChannel = null;
    private String userAccount = null;
    private String clientId = null;
    private long lastSendTime = 0;//время последней отправки сообщения

    private ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
    private UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
    private MessageHeaderEncoder headerEncoder = new MessageHeaderEncoder();

    private int bufferOffset = 0;
    private int encodingLength = 0;

    private String hostAddress = null;
    private int connectionPort;

    //decoders
    private EstablishmentAckDecoder establishmentAckDecoder = new EstablishmentAckDecoder();
    private SequenceDecoder sequenceDecoder = new SequenceDecoder();
    private TerminateDecoder terminateDecoder = new TerminateDecoder();
    private OrderMassCancelResponseDecoder orderMassCancelResponseDecoder = new OrderMassCancelResponseDecoder();
    private SessionRejectDecoder sessionRejectDecoder = new SessionRejectDecoder();
    private NewOrderRejectDecoder newOrderRejectDecoder = new NewOrderRejectDecoder();
    private NewOrderSingleResponseDecoder newOrderSingleResponseDecoder = new NewOrderSingleResponseDecoder();
    private OrderCancelResponseDecoder orderCancelResponseDecoder = new OrderCancelResponseDecoder();
    private ExecutionSingleReportDecoder executionSingleReportDecoder = new ExecutionSingleReportDecoder();

    //encoders
    private EstablishEncoder establishEncoder = new EstablishEncoder();
    private OrderMassCancelRequestEncoder orderMassCancelRequestEncoder = new OrderMassCancelRequestEncoder();
    private NewOrderSingleEncoder newOrderSingleEncoder = new NewOrderSingleEncoder();

    protected BigDecimal priceMultiplier = new BigDecimal(100000);

    private ReadSocketProcess readSocketProcess = null;

    @Override
    public void run() {
        SimpleSocketClient simpleSocketClient = new SimpleSocketClient(this.hostAddress, this.connectionPort);
        simpleSocketClient.doConnect();
        if (simpleSocketClient.isConnected()){
            try {
                outputChannel = Channels.newChannel(simpleSocketClient.getSocket().getOutputStream());
                 readSocketProcess = new ReadSocketProcess(simpleSocketClient.getSocket()){
                    @Override
                    protected void processMessage(int actualReaded) {
                        super.processMessage(actualReaded);
                        AbstractTwimeClient.this.decodeMessage(unsafeBuffer);
                    }

                    @Override
                    protected void onStop() {
                        super.onStop();
                        AbstractTwimeClient.this.stopHeartBeatProcess();
                    }
                };

                Thread connectionThread = new Thread(readSocketProcess);
                connectionThread.start();

                this.sendEstablish();
                try {
                    connectionThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                simpleSocketClient.doDisconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopConnectionProcess(){
        if (readSocketProcess != null && !readSocketProcess.isStopped()){
            readSocketProcess.setStopped(true);
        }
    }

    public void sendNewOrderSingle(long clOrdId, int securityId, double price, long amount, int clOrdLinkId, TimeInForceEnum timeInForce, SideEnum side) throws IOException {
        bufferOffset = encodingLength = 0;
        byteBuffer.clear();

        headerEncoder.wrap(directBuffer, bufferOffset)
                .blockLength(newOrderSingleEncoder.sbeBlockLength())
                .templateId(newOrderSingleEncoder.sbeTemplateId())
                .schemaId(newOrderSingleEncoder.sbeSchemaId())
                .version(newOrderSingleEncoder.sbeSchemaVersion());
        bufferOffset += headerEncoder.encodedLength();
        encodingLength += headerEncoder.encodedLength();

        newOrderSingleEncoder.wrap(directBuffer, bufferOffset);
        long longPrice = new BigDecimal(price).multiply(priceMultiplier).longValue();
        newOrderSingleEncoder.price().mantissa(longPrice);
        newOrderSingleEncoder.account(userAccount).clOrdID(clOrdId).clOrdLinkID(clOrdLinkId).orderQty(amount).securityID(securityId).timeInForce(timeInForce).side(side).checkLimit(CheckLimitEnum.Check).expireDate(NewOrderSingleEncoder.expireDateNullValue());

        encodingLength += newOrderSingleEncoder.encodedLength();
        sendBuffer(encodingLength);
    }

    public void sendOrderMassCancelRequest(long clOrdId, int clOrdLinkID, int securityId, SecurityTypeEnum securityTypeEnum, SideEnum sideEnum, String securityGroup) throws IOException {
        bufferOffset = encodingLength = 0;
        byteBuffer.clear();

        headerEncoder.wrap(directBuffer, bufferOffset)
                .blockLength(orderMassCancelRequestEncoder.sbeBlockLength())
                .templateId(orderMassCancelRequestEncoder.sbeTemplateId())
                .schemaId(orderMassCancelRequestEncoder.sbeSchemaId())
                .version(orderMassCancelRequestEncoder.sbeSchemaVersion());
        bufferOffset += headerEncoder.encodedLength();
        encodingLength += headerEncoder.encodedLength();

        orderMassCancelRequestEncoder.wrap(directBuffer, bufferOffset);
        orderMassCancelRequestEncoder.account(userAccount).clOrdID(clOrdId).clOrdLinkID(clOrdLinkID).securityID(securityId).securityType(securityTypeEnum).side(sideEnum).securityGroup(securityGroup);

        encodingLength += orderMassCancelRequestEncoder.encodedLength();
        sendBuffer(encodingLength);
    }

    public void sendEstablish() throws IOException {
        bufferOffset = encodingLength = 0;
        if (outputChannel != null) {
            byteBuffer.clear();
            headerEncoder.wrap(directBuffer, bufferOffset)
                    .blockLength(establishEncoder.sbeBlockLength())
                    .templateId(establishEncoder.sbeTemplateId())
                    .schemaId(establishEncoder.sbeSchemaId())
                    .version(establishEncoder.sbeSchemaVersion());

            bufferOffset += headerEncoder.encodedLength();
            encodingLength += headerEncoder.encodedLength();
            establishEncoder.wrap(directBuffer, bufferOffset).credentials(clientId).keepaliveInterval(intervalMsec).timestamp(System.currentTimeMillis());
            encodingLength += establishEncoder.encodedLength();
            sendBuffer(encodingLength);
        }
    }

    private void sendBuffer(int length) throws IOException {
        byteBuffer.limit(length);
        outputChannel.write(byteBuffer);
        lastSendTime = System.currentTimeMillis();
    }

    public synchronized void decodeMessage(UnsafeBuffer unsafeBuffer){
        int bytesOffset = 0;
        messageHeaderDecoder.wrap(unsafeBuffer, bytesOffset);

        int templateId = messageHeaderDecoder.templateId();
        int schemaId = messageHeaderDecoder.schemaId();
        int version = messageHeaderDecoder.version();
        int blockLength = messageHeaderDecoder.blockLength();

        bytesOffset += messageHeaderDecoder.encodedLength();
        if (templateId > 0 && version > 0) {

            if (SequenceDecoder.TEMPLATE_ID != templateId) {
                System.out.println("  <<<< AbstractTwimeClient decodeMessage, schemaId: " + schemaId + " |version: " + version + " |templateId: " + templateId + " |blockLength: " + blockLength);
            } else {
                sequenceDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                System.out.println(" .............. < heartbeat < ..............  nextSeqNo: " + sequenceDecoder.nextSeqNo());
            }

            switch (templateId) {
                case EstablishmentAckDecoder.TEMPLATE_ID: //establishmentAsk (начало сессии)
                    establishmentAckDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onEstablishmentAck(establishmentAckDecoder);
                    break;
                //case SequenceDecoder.TEMPLATE_ID://heartbeat (sequence)
                    //sequenceDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    //System.out.println("sequence nextSeqNo: " + sequenceDecoder.nextSeqNo());
                    //break;
                case TerminateDecoder.TEMPLATE_ID: //terminate
                    terminateDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onTerminate(terminateDecoder);
                    break;
                case OrderMassCancelResponseDecoder.TEMPLATE_ID:
                    orderMassCancelResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onOrderMassCancelResponse(orderMassCancelResponseDecoder);
                    break;
                case SessionRejectDecoder.TEMPLATE_ID:
                    sessionRejectDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onSessionReject(sessionRejectDecoder);
                    break;
                case NewOrderRejectDecoder.TEMPLATE_ID:
                    newOrderRejectDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onNewOrderReject(newOrderRejectDecoder);
                    break;
                case NewOrderSingleResponseDecoder.TEMPLATE_ID:
                    newOrderSingleResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onNewOrderSingleResponse(newOrderSingleResponseDecoder);
                    break;
                case OrderCancelResponseDecoder.TEMPLATE_ID:
                    orderCancelResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onOrderCancelResponse(orderCancelResponseDecoder);
                    break;
                case ExecutionSingleReportDecoder.TEMPLATE_ID:
                    executionSingleReportDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onExecutionSingleReport(executionSingleReportDecoder);
                    break;
            }
        }
    }

    protected void onEstablishmentAck(EstablishmentAckDecoder decoder){
        sequenceNum = establishmentAckDecoder.nextSeqNo();
        if (heartBeatProcess == null) {
            heartBeatProcess = new TwimeHeartBeatProcess(sequenceNum, outputChannel, intervalMsec);
        }
        new Thread(heartBeatProcess).start();
    }

    protected void onTerminate(TerminateDecoder decoder){}
    protected void onOrderMassCancelResponse(OrderMassCancelResponseDecoder decoder){}
    protected void onSessionReject(SessionRejectDecoder decoder){}
    protected void onNewOrderReject(NewOrderRejectDecoder decoder){}
    protected void onNewOrderSingleResponse(NewOrderSingleResponseDecoder decoder){}
    protected void onOrderCancelResponse(OrderCancelResponseDecoder decoder){}
    protected void onExecutionSingleReport(ExecutionSingleReportDecoder decoder){}


    public TwimeHeartBeatProcess getHeartBeatProcess() {
        return heartBeatProcess;
    }

    public long getIntervalMsec() {
        return intervalMsec;
    }

    public AbstractTwimeClient setIntervalMsec(long intervalMsec) {
        this.intervalMsec = intervalMsec;
        return this;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public AbstractTwimeClient setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public AbstractTwimeClient setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public long getLastSendTime() {
        return lastSendTime;
    }

    public void stopHeartBeatProcess(){
        if (this.getHeartBeatProcess() != null && !this.getHeartBeatProcess().isStopped()) {
            this.getHeartBeatProcess().setStopped(true);
        }
    }

    public long generateNewClientOrderId(){
        return System.currentTimeMillis();
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public AbstractTwimeClient setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
        return this;
    }

    public int getConnectionPort() {
        return connectionPort;
    }

    public AbstractTwimeClient setConnectionPort(int connectionPort) {
        this.connectionPort = connectionPort;
        return this;
    }
}

