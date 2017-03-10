package SimpleIOSocket;

import org.agrona.concurrent.UnsafeBuffer;
import sbe.*;

import java.io.IOException;
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

    private ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
    private UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
    private MessageHeaderEncoder MESSAGE_HEADER_ENCODER = new MessageHeaderEncoder();

    private int bufferOffset = 0;
    private int encodingLength = 0;

    private EstablishmentAckDecoder establishmentAckDecoder = new EstablishmentAckDecoder();
    private SequenceDecoder sequenceDecoder = new SequenceDecoder();
    private TerminateDecoder terminateDecoder = new TerminateDecoder();
    private OrderMassCancelResponseDecoder orderMassCancelResponseDecoder = new OrderMassCancelResponseDecoder();

    private EstablishEncoder establishEncoder = new EstablishEncoder();
    private OrderMassCancelRequestEncoder orderMassCancelRequestEncoder = new OrderMassCancelRequestEncoder();

    public void sendOrderMassCancelRequest(long clOrdId, int clOrdLinkID, int securityId, SecurityTypeEnum securityTypeEnum, SideEnum sideEnum, String securityGroup) throws IOException {
        bufferOffset = encodingLength = 0;
        byteBuffer.clear();

        MESSAGE_HEADER_ENCODER
                .wrap(directBuffer, bufferOffset)
                .blockLength(orderMassCancelRequestEncoder.sbeBlockLength())
                .templateId(orderMassCancelRequestEncoder.sbeTemplateId())
                .schemaId(orderMassCancelRequestEncoder.sbeSchemaId())
                .version(orderMassCancelRequestEncoder.sbeSchemaVersion());
        bufferOffset += MESSAGE_HEADER_ENCODER.encodedLength();
        encodingLength += MESSAGE_HEADER_ENCODER.encodedLength();
        orderMassCancelRequestEncoder.wrap(directBuffer, bufferOffset);
        orderMassCancelRequestEncoder.account(userAccount).clOrdID(clOrdId).clOrdLinkID(clOrdLinkID).securityID(securityId).securityType(securityTypeEnum).side(sideEnum).securityGroup(securityGroup);
        encodingLength += orderMassCancelRequestEncoder.encodedLength();
        sendBuffer(encodingLength);
    }

    public void sendEstablish(String clientId) throws IOException {
        bufferOffset = encodingLength = 0;
        if (outputChannel != null) {
            byteBuffer.clear();
            MESSAGE_HEADER_ENCODER
                    .wrap(directBuffer, bufferOffset)
                    .blockLength(establishEncoder.sbeBlockLength())
                    .templateId(establishEncoder.sbeTemplateId())
                    .schemaId(establishEncoder.sbeSchemaId())
                    .version(establishEncoder.sbeSchemaVersion());

            bufferOffset += MESSAGE_HEADER_ENCODER.encodedLength();
            encodingLength += MESSAGE_HEADER_ENCODER.encodedLength();
            establishEncoder.wrap(directBuffer, bufferOffset).credentials(clientId).keepaliveInterval(intervalMsec).timestamp(System.currentTimeMillis());
            encodingLength += establishEncoder.encodedLength();
            sendBuffer(encodingLength);
        }
    }

    private void sendBuffer(int length) throws IOException {
        byteBuffer.limit(length);
        outputChannel.write(byteBuffer);
    }

    public void decodeMessage(UnsafeBuffer unsafeBuffer){
        int bytesOffset = 0;
        messageHeaderDecoder.wrap(unsafeBuffer, bytesOffset);

        int templateId = messageHeaderDecoder.templateId();
        int schemaId = messageHeaderDecoder.schemaId();
        int version = messageHeaderDecoder.version();
        int blockLength = messageHeaderDecoder.blockLength();

        System.out.println("  <<<< TwimeClient decodeMessage, schemaId: " + schemaId + " |version: " + version + " |templateId: " + templateId + " |blockLength: " + blockLength);

        bytesOffset += messageHeaderDecoder.encodedLength();
        if (templateId > 0 && version > 0) {
            switch (templateId) {
                case EstablishmentAckDecoder.TEMPLATE_ID: //establishmentAsk (начало сессии)
                    establishmentAckDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    sequenceNum = establishmentAckDecoder.nextSeqNo();
                    if (heartBeatProcess == null) {
                        heartBeatProcess = new TwimeHeartBeatProcess(sequenceNum, outputChannel, intervalMsec);
                    }
                    new Thread(heartBeatProcess).start();
                    break;
                case SequenceDecoder.TEMPLATE_ID://heartbeat (sequence)
                    sequenceDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("sequence nextSeqNo: " + sequenceDecoder.nextSeqNo());
                    break;
                case TerminateDecoder.TEMPLATE_ID: //terminate
                    terminateDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("terminate, reason: " + terminateDecoder.terminationCode().toString());
                    break;
                case OrderMassCancelResponseDecoder.TEMPLATE_ID:
                    orderMassCancelResponseDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("OrderMassCancelResponse, totalaffected: " + orderMassCancelResponseDecoder.totalAffectedOrders() + " |reject reason: " + orderMassCancelResponseDecoder.ordRejReason());
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
}
