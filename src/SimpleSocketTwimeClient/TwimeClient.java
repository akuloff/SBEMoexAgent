package SimpleSocketTwimeClient;

import com.alfajavatrading.TradePrimitives.TradeOrder;
import org.agrona.concurrent.UnsafeBuffer;
import sbe.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/**
 * Created by mpoke_000 on 08.03.2017.
 */
public class TwimeClient {
    private MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
    private long sequenceNum = 0;
    private TwimeHeartBeatProcess heartBeatProcess = null;
    private long intervalMsec = 0;
    private WritableByteChannel outputChannel = null;
    private String userAccount = null;
    private long lastSendTime = 0;//время последней отправки сообщения

    private ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
    private UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
    private MessageHeaderEncoder headerEncoder = new MessageHeaderEncoder();

    private int bufferOffset = 0;
    private int encodingLength = 0;

    //decoders
    private EstablishmentAckDecoder establishmentAckDecoder = new EstablishmentAckDecoder();
    private SequenceDecoder sequenceDecoder = new SequenceDecoder();
    private TerminateDecoder terminateDecoder = new TerminateDecoder();
    private OrderMassCancelResponseDecoder orderMassCancelResponseDecoder = new OrderMassCancelResponseDecoder();
    private SessionRejectDecoder sessionRejectDecoder = new SessionRejectDecoder();
    private NewOrderRejectDecoder newOrderRejectDecoder = new NewOrderRejectDecoder();
    private NewOrderSingleResponseDecoder newOrderSingleResponseDecoder = new NewOrderSingleResponseDecoder();
    private OrderCancelResponseDecoder orderCancelResponseDecoder = new OrderCancelResponseDecoder();

    //encoders
    private EstablishEncoder establishEncoder = new EstablishEncoder();
    private OrderMassCancelRequestEncoder orderMassCancelRequestEncoder = new OrderMassCancelRequestEncoder();
    private NewOrderSingleEncoder newOrderSingleEncoder = new NewOrderSingleEncoder();

    private BigDecimal priceMultiplication = new BigDecimal(100000);

    public void sendNewOrderSingle(long clOrdId, double price, long amount, int securityId, int clOrdLinkId, TimeInForceEnum timeInForce, SideEnum side) throws IOException {
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
        long longPrice = new BigDecimal(price).multiply(priceMultiplication).longValue();
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

    public void sendEstablish(String clientId) throws IOException {
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

    public void decodeMessage(UnsafeBuffer unsafeBuffer){
        int bytesOffset = 0;
        messageHeaderDecoder.wrap(unsafeBuffer, bytesOffset);

        int templateId = messageHeaderDecoder.templateId();
        int schemaId = messageHeaderDecoder.schemaId();
        int version = messageHeaderDecoder.version();
        int blockLength = messageHeaderDecoder.blockLength();

        bytesOffset += messageHeaderDecoder.encodedLength();
        if (templateId > 0 && version > 0) {

            if (SequenceDecoder.TEMPLATE_ID != templateId) {
                System.out.println("  <<<< TwimeClient decodeMessage, schemaId: " + schemaId + " |version: " + version + " |templateId: " + templateId + " |blockLength: " + blockLength);
            } else {
                sequenceDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                System.out.println(" .............. < heartbeat < ..............  nextSeqNo: " + sequenceDecoder.nextSeqNo());
            }

            switch (templateId) {
                case EstablishmentAckDecoder.TEMPLATE_ID: //establishmentAsk (начало сессии)
                    establishmentAckDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    sequenceNum = establishmentAckDecoder.nextSeqNo();
                    if (heartBeatProcess == null) {
                        heartBeatProcess = new TwimeHeartBeatProcess(sequenceNum, outputChannel, intervalMsec);
                    }
                    new Thread(heartBeatProcess).start();
                    break;
                //case SequenceDecoder.TEMPLATE_ID://heartbeat (sequence)
                    //sequenceDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    //System.out.println("sequence nextSeqNo: " + sequenceDecoder.nextSeqNo());
                    //break;
                case TerminateDecoder.TEMPLATE_ID: //terminate
                    terminateDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("terminate, reason: " + terminateDecoder.terminationCode().toString());
                    break;
                case OrderMassCancelResponseDecoder.TEMPLATE_ID:
                    orderMassCancelResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("OrderMassCancelResponse, totalaffected: " + orderMassCancelResponseDecoder.totalAffectedOrders() + " |reject reason: " + orderMassCancelResponseDecoder.ordRejReason());
                    break;
                case SessionRejectDecoder.TEMPLATE_ID:
                    sessionRejectDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("SessionReject, clordid: " + sessionRejectDecoder.clOrdID() + " |refTagId: " + sessionRejectDecoder.refTagID() + " |reason: " + sessionRejectDecoder.sessionRejectReason().toString());
                    break;
                case NewOrderRejectDecoder.TEMPLATE_ID:
                    newOrderRejectDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("NewOrderReject, clOrdId: " + newOrderRejectDecoder.clOrdID() + " |reason(code): " + newOrderRejectDecoder.ordRejReason());
                    break;
                case NewOrderSingleResponseDecoder.TEMPLATE_ID:
                    newOrderSingleResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    //TradeOrder order = new TradeOrder();
                    //order.setClientOrderId("" + newOrderSingleResponseDecoder.clOrdID());

                    System.out.println("newOrderSingleResponse ....");
                    System.out.println("clOrdID: " + newOrderSingleResponseDecoder.clOrdID());
                    System.out.println("timestamp: " + new java.util.Date(newOrderSingleResponseDecoder.timestamp()));
                    System.out.println("ExpireDate: " + new java.util.Date(newOrderSingleResponseDecoder.expireDate()));
                    System.out.println("OrderID: " + newOrderSingleResponseDecoder.orderID());
                    System.out.println("price: " + newOrderSingleResponseDecoder.price().toString());
                    System.out.println("SecurityID: " + newOrderSingleResponseDecoder.securityID());
                    System.out.println("OrderQty: " + newOrderSingleResponseDecoder.orderQty());
                    System.out.println("TradingSessionID: " + newOrderSingleResponseDecoder.tradingSessionID());
                    System.out.println("ClOrdLinkID: " + newOrderSingleResponseDecoder.clOrdLinkID());
                    System.out.println("Side: " + newOrderSingleResponseDecoder.side().toString());

                    break;
                case OrderCancelResponseDecoder.TEMPLATE_ID:
                    orderCancelResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("orderCancelResponse ....");
                    System.out.println("clOrdId: " + orderCancelResponseDecoder.clOrdID());
                    System.out.println("Timestamp: " + new java.util.Date(orderCancelResponseDecoder.timestamp()));
                    System.out.println("orderId: " + orderCancelResponseDecoder.orderID());
                    System.out.println("Flags: " + orderCancelResponseDecoder.flags());
                    System.out.println("OrderQty: " + orderCancelResponseDecoder.orderQty());
                    System.out.println("TradingSessionID: " + orderCancelResponseDecoder.tradingSessionID());
                    System.out.println("ClOrdLinkID: " + orderCancelResponseDecoder.clOrdLinkID());
                    break;
            }
        }
    }

    public TwimeHeartBeatProcess getHeartBeatProcess() {
        return heartBeatProcess;
    }

    public long getIntervalMsec() {
        return intervalMsec;
    }

    public TwimeClient setIntervalMsec(long intervalMsec) {
        this.intervalMsec = intervalMsec;
        return this;
    }

    public WritableByteChannel getOutputChannel() {
        return outputChannel;
    }

    public TwimeClient setOutputChannel(WritableByteChannel outputChannel) {
        this.outputChannel = outputChannel;
        return this;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public TwimeClient setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public long getLastSendTime() {
        return lastSendTime;
    }
}

