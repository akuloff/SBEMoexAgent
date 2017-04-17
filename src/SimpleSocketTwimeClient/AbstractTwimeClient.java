package SimpleSocketTwimeClient;

import org.agrona.concurrent.UnsafeBuffer;
import sbe.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

/**
 * Created by mpoke_000 on 08.03.2017.
 */
public class AbstractTwimeClient implements Runnable{
    private MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
    protected long receivedSequenceNum = 0; //счетчик сообщений прикладного уровня
    protected long retransmissionCount = 0;

    private TwimeHeartBeatProcess heartBeatProcess = null;
    private long intervalMsec = 0;
    private WritableByteChannel outputChannel = null;
    private String userAccount = null;
    private String clientId = null;
    private long lastSendTime = 0;//время последней отправки сообщения (любого) - в т.ч. для определени янеобходимости отправки heartbeat

    private ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
    private UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
    private int bufferOffset = 0;
    private int encodingLength = 0;

    private String hostAddress = null;
    private int connectionPort;

    //decoders
    private EstablishmentAckDecoder establishmentAckDecoder = new EstablishmentAckDecoder();
    private EstablishmentRejectDecoder establishmentRejectDecoder = new EstablishmentRejectDecoder();
    private SequenceDecoder sequenceDecoder = new SequenceDecoder();
    private TerminateDecoder terminateDecoder = new TerminateDecoder();
    private OrderMassCancelResponseDecoder orderMassCancelResponseDecoder = new OrderMassCancelResponseDecoder();
    private SessionRejectDecoder sessionRejectDecoder = new SessionRejectDecoder();
    private RetransmissionDecoder retransmissionDecoder = new RetransmissionDecoder();
    private NewOrderRejectDecoder newOrderRejectDecoder = new NewOrderRejectDecoder();
    private NewOrderSingleResponseDecoder newOrderSingleResponseDecoder = new NewOrderSingleResponseDecoder();
    private OrderCancelResponseDecoder orderCancelResponseDecoder = new OrderCancelResponseDecoder();
    private ExecutionSingleReportDecoder executionSingleReportDecoder = new ExecutionSingleReportDecoder();
    private SystemEventDecoder systemEventDecoder = new SystemEventDecoder();
    private EmptyBookDecoder emptyBookDecoder = new EmptyBookDecoder();

    //encoders
    private MessageHeaderEncoder headerEncoder = new MessageHeaderEncoder();
    private EstablishEncoder establishEncoder = new EstablishEncoder();
    private RetransmitRequestEncoder retransmitRequestEncoder = new RetransmitRequestEncoder();
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
                        int currentOffset = 0;
                        while(currentOffset < actualReaded) {
                            currentOffset = AbstractTwimeClient.this.decodeMessage(unsafeBuffer, currentOffset);
                        }
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

                AbstractTwimeClient.this.stopHeartBeatProcess();
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

    private void initSender(int templateId, int blockLength){
        bufferOffset = encodingLength = 0;
        byteBuffer.clear();
        headerEncoder.wrap(directBuffer, bufferOffset)
                .blockLength(blockLength)
                .templateId(templateId)
                .schemaId(establishEncoder.sbeSchemaId())
                .version(establishEncoder.sbeSchemaVersion());
        bufferOffset += headerEncoder.encodedLength();
        encodingLength += headerEncoder.encodedLength();
    }

    public void sendNewOrderSingle(long clOrdId, int securityId, double price, long amount, int clOrdLinkId, TimeInForceEnum timeInForce, SideEnum side) throws IOException {
        initSender(newOrderSingleEncoder.sbeTemplateId(), newOrderSingleEncoder.sbeBlockLength());
        newOrderSingleEncoder.wrap(directBuffer, bufferOffset);
        long longPrice = new BigDecimal(price).multiply(priceMultiplier).longValue();
        newOrderSingleEncoder.price().mantissa(longPrice);
        newOrderSingleEncoder.account(userAccount).clOrdID(clOrdId).clOrdLinkID(clOrdLinkId).orderQty(amount).securityID(securityId).timeInForce(timeInForce).side(side).checkLimit(CheckLimitEnum.Check).expireDate(NewOrderSingleEncoder.expireDateNullValue());
        encodingLength += newOrderSingleEncoder.encodedLength();
        sendBuffer(encodingLength);
    }

    public void sendOrderMassCancelRequest(long clOrdId, int clOrdLinkID, int securityId, SecurityTypeEnum securityTypeEnum, SideEnum sideEnum, String securityGroup) throws IOException {
        initSender(orderMassCancelRequestEncoder.sbeTemplateId(), orderMassCancelRequestEncoder.sbeBlockLength());
        orderMassCancelRequestEncoder.wrap(directBuffer, bufferOffset).account(userAccount).clOrdID(clOrdId).clOrdLinkID(clOrdLinkID).securityID(securityId).securityType(securityTypeEnum).side(sideEnum).securityGroup(securityGroup);
        encodingLength += orderMassCancelRequestEncoder.encodedLength();
        sendBuffer(encodingLength);
    }

    public void sendEstablish() throws IOException {
        if (outputChannel != null) {
            initSender(establishEncoder.sbeTemplateId(), establishEncoder.sbeBlockLength());
            establishEncoder.wrap(directBuffer, bufferOffset).credentials(clientId).keepaliveInterval(intervalMsec).timestamp(System.currentTimeMillis());
            encodingLength += establishEncoder.encodedLength();
            sendBuffer(encodingLength);
        }
    }

    public void sendRetransmitRequest(long timestamp, long fromSeqNum, long count) throws IOException{
        initSender(retransmitRequestEncoder.sbeTemplateId(), retransmitRequestEncoder.sbeBlockLength());
        retransmitRequestEncoder.wrap(directBuffer, bufferOffset).timestamp(timestamp).fromSeqNo(fromSeqNum).count(count);
        encodingLength += retransmitRequestEncoder.encodedLength();
        sendBuffer(encodingLength);
    }

    private void sendBuffer(int length) throws IOException {
        byteBuffer.limit(length);
        outputChannel.write(byteBuffer);
        lastSendTime = System.currentTimeMillis();
    }

    private void increaseSequence(){
        if (retransmissionCount > 0) {
            System.out.println(" ----- message from retransmission, retransmissionCount: " + retransmissionCount);
            retransmissionCount --;
        } else {
            receivedSequenceNum ++;
        }
    }

    public synchronized int decodeMessage(UnsafeBuffer unsafeBuffer, int startOffset){
        int bytesOffset = startOffset;
        messageHeaderDecoder.wrap(unsafeBuffer, bytesOffset);

        int templateId = messageHeaderDecoder.templateId();
        int schemaId = messageHeaderDecoder.schemaId();
        int version = messageHeaderDecoder.version();
        int blockLength = messageHeaderDecoder.blockLength();

        bytesOffset += messageHeaderDecoder.encodedLength();
        if (templateId > 0 && version > 0) {
            if (SequenceDecoder.TEMPLATE_ID != templateId) {
                System.out.println("  <<<< AbstractTwimeClient decodeMessage, schemaId: " + schemaId + " |version: " + version + " |templateId: " + templateId + " |blockLength: " + blockLength + " |bytesOffset: " + bytesOffset);
            }

            switch (templateId) {

                //Сессионный уровень (соединение)

                case EstablishmentAckDecoder.TEMPLATE_ID: //establishmentAsk (начало сессии)
                    establishmentAckDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onEstablishmentAck(establishmentAckDecoder);
                    break;
                case EstablishmentRejectDecoder.TEMPLATE_ID:
                    establishmentRejectDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onEstablishmentReject(establishmentRejectDecoder);
                    break;
                case SequenceDecoder.TEMPLATE_ID://heartbeat (sequence)
                    sequenceDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onSequence(sequenceDecoder);
                    break;
                case TerminateDecoder.TEMPLATE_ID: //terminate
                    terminateDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onTerminate(terminateDecoder);
                    break;
                case SessionRejectDecoder.TEMPLATE_ID:
                    sessionRejectDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onSessionReject(sessionRejectDecoder);
                    break;
                case RetransmissionDecoder.TEMPLATE_ID:
                    retransmissionDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    retransmissionCount = retransmissionDecoder.count();
                    onRetransmission(retransmissionDecoder);
                    break;

                // Прикладной уровень (торговля) - увеличивает sequenceNum

                case OrderMassCancelResponseDecoder.TEMPLATE_ID:
                    orderMassCancelResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onOrderMassCancelResponse(orderMassCancelResponseDecoder);
                    increaseSequence();
                    break;
                case NewOrderRejectDecoder.TEMPLATE_ID:
                    newOrderRejectDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onNewOrderReject(newOrderRejectDecoder);
                    increaseSequence();
                    break;
                case NewOrderSingleResponseDecoder.TEMPLATE_ID:
                    newOrderSingleResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onNewOrderSingleResponse(newOrderSingleResponseDecoder);
                    increaseSequence();
                    break;
                case OrderCancelResponseDecoder.TEMPLATE_ID:
                    orderCancelResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onOrderCancelResponse(orderCancelResponseDecoder);
                    increaseSequence();
                    break;
                case ExecutionSingleReportDecoder.TEMPLATE_ID:
                    executionSingleReportDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onExecutionSingleReport(executionSingleReportDecoder);
                    increaseSequence();
                    break;
                case SystemEventDecoder.TEMPLATE_ID:
                    systemEventDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onSystemEvent(systemEventDecoder);
                    increaseSequence();
                    break;
                case EmptyBookDecoder.TEMPLATE_ID:
                    emptyBookDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onEmptyBook(emptyBookDecoder);
                    increaseSequence();
                    break;
            }

            bytesOffset += blockLength;
        }

        return bytesOffset;
    }

    protected void onEstablishmentAck(EstablishmentAckDecoder decoder){
        receivedSequenceNum = decoder.nextSeqNo();
        if (heartBeatProcess == null) {
            heartBeatProcess = new TwimeHeartBeatProcess(outputChannel, intervalMsec);
        }
        new Thread(heartBeatProcess).start();
    }

    //сессионные обработчики
    protected void onTerminate(TerminateDecoder decoder){}
    protected void onOrderMassCancelResponse(OrderMassCancelResponseDecoder decoder){}
    protected void onSessionReject(SessionRejectDecoder decoder){}
    protected void onEstablishmentReject(EstablishmentRejectDecoder decoder){}
    protected void onSequence(SequenceDecoder decoder){}
    protected void onRetransmission(RetransmissionDecoder decoder){}

    //прикладные обработчики
    protected void onNewOrderReject(NewOrderRejectDecoder decoder){}
    protected void onNewOrderSingleResponse(NewOrderSingleResponseDecoder decoder){}
    protected void onOrderCancelResponse(OrderCancelResponseDecoder decoder){}
    protected void onExecutionSingleReport(ExecutionSingleReportDecoder decoder){}
    protected void onSystemEvent(SystemEventDecoder decoder){}
    protected void onEmptyBook(EmptyBookDecoder decoder){}


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

    public long getReceivedSequenceNum() {
        return receivedSequenceNum;
    }

    public AbstractTwimeClient setReceivedSequenceNum(long receivedSequenceNum) {
        this.receivedSequenceNum = receivedSequenceNum;
        return this;
    }

    public long getRetransmissionCount() {
        return retransmissionCount;
    }
}

