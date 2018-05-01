package com.monkeyquant.twime;

import generated.sbe.*;
import org.agrona.concurrent.UnsafeBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

public abstract class AbstractTwimeClient {
    private static final Logger logger = Logger.getLogger(AbstractTwimeClient.class.getName());

    private MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
    protected long receivedSequenceNum = 0; //счетчик сообщений прикладного уровня
    protected long retransmissionCount = 0;
    protected long keepAliveInterval = 5000;

    private String userAccount = null;
    private String clientId = null;

    private ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
    private UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);

    private ByteBuffer heartBeatBuffer = ByteBuffer.allocateDirect(4096);
    private UnsafeBuffer directHearBeatBuffer = new UnsafeBuffer(heartBeatBuffer);
    private boolean isHeartBeatCreated = false;

    private int bufferOffset = 0;
    private int encodingLength = 0;
    private int heartBeatLength = 0;


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
    private NewOrderMultilegResponseDecoder newOrderMultilegResponseDecoder = new NewOrderMultilegResponseDecoder();
    private OrderCancelResponseDecoder orderCancelResponseDecoder = new OrderCancelResponseDecoder();
    private ExecutionSingleReportDecoder executionSingleReportDecoder = new ExecutionSingleReportDecoder();
    private ExecutionMultilegReportDecoder executionMultilegReportDecoder = new ExecutionMultilegReportDecoder();
    private SystemEventDecoder systemEventDecoder = new SystemEventDecoder();
    private EmptyBookDecoder emptyBookDecoder = new EmptyBookDecoder();
    private OrderReplaceResponseDecoder orderReplaceResponseDecoder = new OrderReplaceResponseDecoder();
    private FloodRejectDecoder floodRejectDecoder = new FloodRejectDecoder();

    //encoders
    private MessageHeaderEncoder headerEncoder = new MessageHeaderEncoder();
    private EstablishEncoder establishEncoder = new EstablishEncoder();
    private RetransmitRequestEncoder retransmitRequestEncoder = new RetransmitRequestEncoder();
    private OrderMassCancelRequestEncoder orderMassCancelRequestEncoder = new OrderMassCancelRequestEncoder();
    private NewOrderSingleEncoder newOrderSingleEncoder = new NewOrderSingleEncoder();
    private NewOrderMultilegEncoder newOrderMultilegEncoder = new NewOrderMultilegEncoder();
    private OrderCancelRequestEncoder orderCancelRequestEncoder = new OrderCancelRequestEncoder();
    private OrderReplaceRequestEncoder orderReplaceRequestEncoder = new OrderReplaceRequestEncoder();

    protected long priceMultiplier = 100000L;

    public long getKeepAliveInterval() {
        return keepAliveInterval;
    }

    public AbstractTwimeClient setKeepAliveInterval(long keepAliveInterval) {
        this.keepAliveInterval = keepAliveInterval;
        return this;
    }


    private void initHeaderForSend(int templateId, int blockLength){
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


    protected void sendHeartBeat() throws IOException {
        if (!isHeartBeatCreated) {
            MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
            SequenceEncoder sequenceEncoder = new SequenceEncoder();
            messageHeaderEncoder.wrap(directHearBeatBuffer, 0)
                    .blockLength(sequenceEncoder.sbeBlockLength())
                    .templateId(sequenceEncoder.sbeTemplateId())
                    .schemaId(sequenceEncoder.sbeSchemaId())
                    .version(sequenceEncoder.sbeSchemaVersion());
            heartBeatLength += messageHeaderEncoder.encodedLength();
            sequenceEncoder.wrap(directBuffer, heartBeatLength).nextSeqNo(SequenceEncoder.nextSeqNoNullValue());
            heartBeatLength += sequenceEncoder.encodedLength();
            isHeartBeatCreated = true;
            heartBeatBuffer.limit(encodingLength);
        }
        sendBuffer(heartBeatBuffer, heartBeatLength);
    }

    protected void sendNewOrderSingle(long clOrdId, int securityId, double price, long amount, int clOrdLinkId, TimeInForceEnum timeInForce, SideEnum side) throws IOException {
        initHeaderForSend(newOrderSingleEncoder.sbeTemplateId(), newOrderSingleEncoder.sbeBlockLength());
        newOrderSingleEncoder.wrap(directBuffer, bufferOffset);
        long longPrice = (long) price * priceMultiplier;
        newOrderSingleEncoder.price().mantissa(longPrice);
        newOrderSingleEncoder.account(userAccount).clOrdID(clOrdId).clOrdLinkID(clOrdLinkId).orderQty(amount).securityID(securityId).timeInForce(timeInForce).side(side).checkLimit(CheckLimitEnum.Check).expireDate(NewOrderSingleEncoder.expireDateNullValue());
        encodingLength += newOrderSingleEncoder.encodedLength();
        sendBuffer(byteBuffer, encodingLength);
    }

    protected void sendNewOrderMultileg(long clOrdId, int securityId, double price, long amount, int clOrdLinkId, TimeInForceEnum timeInForce, SideEnum side) throws IOException {
        initHeaderForSend(newOrderMultilegEncoder.sbeTemplateId(), newOrderMultilegEncoder.sbeBlockLength());
        newOrderMultilegEncoder.wrap(directBuffer, bufferOffset);
        long longPrice = (long) price * priceMultiplier;
        newOrderMultilegEncoder.price().mantissa(longPrice);
        newOrderMultilegEncoder.account(userAccount).clOrdID(clOrdId).clOrdLinkID(clOrdLinkId).orderQty(amount).securityID(securityId).timeInForce(timeInForce).side(side).expireDate(NewOrderMultilegEncoder.expireDateNullValue());
        encodingLength += newOrderMultilegEncoder.encodedLength();
        sendBuffer(byteBuffer, encodingLength);
    }

    protected void sendOrderMassCancelRequest(long clOrdId, int clOrdLinkID, int securityId, SecurityTypeEnum securityTypeEnum, SideEnum sideEnum, String securityGroup) throws IOException {
        initHeaderForSend(orderMassCancelRequestEncoder.sbeTemplateId(), orderMassCancelRequestEncoder.sbeBlockLength());
        orderMassCancelRequestEncoder.wrap(directBuffer, bufferOffset).account(userAccount).clOrdID(clOrdId).clOrdLinkID(clOrdLinkID).securityID(securityId).securityType(securityTypeEnum).side(sideEnum).securityGroup(securityGroup);
        encodingLength += orderMassCancelRequestEncoder.encodedLength();
        sendBuffer(byteBuffer, encodingLength);
    }

    protected void sendOrderCancelRequest(long clOrdId, long orderId) throws IOException {
        initHeaderForSend(orderCancelRequestEncoder.sbeTemplateId(), orderCancelRequestEncoder.sbeBlockLength());
        orderCancelRequestEncoder.wrap(directBuffer, bufferOffset);
        orderCancelRequestEncoder.account(userAccount).clOrdID(clOrdId).orderID(orderId);
        encodingLength += orderCancelRequestEncoder.encodedLength();
        sendBuffer(byteBuffer, encodingLength);
    }

    protected void sendOrderReplaceRequest(long clOrdId, long orderId, double newPrice, long newAmount, int clOrdLinkId, ModeEnum mode) throws IOException {
        initHeaderForSend(orderReplaceRequestEncoder.sbeTemplateId(), orderReplaceRequestEncoder.sbeBlockLength());
        orderReplaceRequestEncoder.wrap(directBuffer, bufferOffset);
        long longPrice = (long) newPrice * priceMultiplier;
        orderReplaceRequestEncoder.price().mantissa(longPrice);
        orderReplaceRequestEncoder.account(userAccount).clOrdID(clOrdId).orderID(orderId).orderQty(newAmount).clOrdLinkID(clOrdLinkId).mode(mode);
        encodingLength += orderReplaceRequestEncoder.encodedLength();
        sendBuffer(byteBuffer, encodingLength);
    }

    protected void sendEstablish() throws IOException {
        initHeaderForSend(establishEncoder.sbeTemplateId(), establishEncoder.sbeBlockLength());
        establishEncoder.wrap(directBuffer, bufferOffset).credentials(clientId).keepaliveInterval(keepAliveInterval).timestamp(System.currentTimeMillis());
        encodingLength += establishEncoder.encodedLength();
        sendBuffer(byteBuffer, encodingLength);
    }

    protected void sendRetransmitRequest(long timestamp, long fromSeqNum, long count) throws IOException{
        initHeaderForSend(retransmitRequestEncoder.sbeTemplateId(), retransmitRequestEncoder.sbeBlockLength());
        retransmitRequestEncoder.wrap(directBuffer, bufferOffset).timestamp(timestamp).fromSeqNo(fromSeqNum).count(count);
        encodingLength += retransmitRequestEncoder.encodedLength();
        sendBuffer(byteBuffer, encodingLength);
    }

    abstract protected void sendBuffer(ByteBuffer buffer, int length) throws IOException;

    private void increaseSequence(){
        if (retransmissionCount > 0) {
            logger.fine(String.format(" ----- message from retransmission, retransmissionCount: %1$d", retransmissionCount));
            retransmissionCount --;
        }
        receivedSequenceNum ++;
    }

    protected synchronized int decodeMessage(UnsafeBuffer unsafeBuffer, int startOffset){
        int bytesOffset = startOffset;
        messageHeaderDecoder.wrap(unsafeBuffer, bytesOffset);

        int templateId = messageHeaderDecoder.templateId();
        int schemaId = messageHeaderDecoder.schemaId();
        int version = messageHeaderDecoder.version();
        int blockLength = messageHeaderDecoder.blockLength();

        bytesOffset += messageHeaderDecoder.encodedLength();
        if (templateId > 0 && version > 0) {
            if (SequenceDecoder.TEMPLATE_ID != templateId) {
                logger.fine(String.format("  <<<< decodeMessage, schemaId: %1$d |version: %2$d |templateId: %3$d |blockLength: %4$d |bytesOffset: %5$d", schemaId, version, templateId, blockLength, bytesOffset));
            }

            switch (templateId) {
                //Сессионный уровень (соединение, heartbeat)

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
                case FloodRejectDecoder.TEMPLATE_ID:
                    floodRejectDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onFloodReject(floodRejectDecoder);
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
                case NewOrderMultilegResponseDecoder.TEMPLATE_ID:
                    newOrderMultilegResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onNewOrderMultilegResponse(newOrderMultilegResponseDecoder);
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
                case ExecutionMultilegReportDecoder.TEMPLATE_ID:
                    executionMultilegReportDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onExecutionMultilegReport(executionMultilegReportDecoder);
                    increaseSequence();
                    break;
                case OrderReplaceResponseDecoder.TEMPLATE_ID:
                    orderReplaceResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    onOrderReplaceResponse(orderReplaceResponseDecoder);
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

    abstract protected void onEstablishmentAck(EstablishmentAckDecoder decoder);

    //сессионные обработчики
    abstract protected void onTerminate(TerminateDecoder decoder);
    abstract protected void onOrderMassCancelResponse(OrderMassCancelResponseDecoder decoder);
    abstract protected void onSessionReject(SessionRejectDecoder decoder);
    abstract protected void onEstablishmentReject(EstablishmentRejectDecoder decoder);
    abstract protected void onSequence(SequenceDecoder decoder);
    abstract protected void onRetransmission(RetransmissionDecoder decoder);
    abstract protected void onFloodReject(FloodRejectDecoder decoder);

    //прикладные обработчики
    protected void onNewOrderReject(NewOrderRejectDecoder decoder){}
    protected void onNewOrderSingleResponse(NewOrderSingleResponseDecoder decoder){}
    protected void onNewOrderMultilegResponse(NewOrderMultilegResponseDecoder decoder){}
    protected void onOrderCancelResponse(OrderCancelResponseDecoder decoder){}
    protected void onOrderReplaceResponse(OrderReplaceResponseDecoder decoder){}
    protected void onExecutionSingleReport(ExecutionSingleReportDecoder decoder){}
    protected void onExecutionMultilegReport(ExecutionMultilegReportDecoder decoder){}
    protected void onSystemEvent(SystemEventDecoder decoder){}
    protected void onEmptyBook(EmptyBookDecoder decoder){}


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

    public long generateNewClientOrderId(){
        return System.currentTimeMillis();
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

