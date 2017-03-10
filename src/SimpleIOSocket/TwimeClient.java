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

    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
    UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);
    MessageHeaderEncoder MESSAGE_HEADER_ENCODER = new MessageHeaderEncoder();


    public void sendEstablish(String clientId) throws IOException {
        EstablishEncoder establishEncoder = new EstablishEncoder();
        int bufferOffset = 0;
        int encodingLength = 0;

        if (outputChannel != null) {
            byteBuffer.reset();
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
            byteBuffer.limit(encodingLength);
            outputChannel.write(byteBuffer);
        }
    }


    public void decodeMessage(UnsafeBuffer unsafeBuffer){
        int bytesOffset = 0;
        messageHeaderDecoder.wrap(unsafeBuffer, bytesOffset);

        int templateId = messageHeaderDecoder.templateId();
        int schemaId = messageHeaderDecoder.schemaId();
        int version = messageHeaderDecoder.version();
        int blockLength = messageHeaderDecoder.blockLength();

        System.out.println("TwimeClient, schemaId: " + schemaId + " |version: " + version + " |templateId: " + templateId + " |blockLength: " + blockLength);

        bytesOffset += messageHeaderDecoder.encodedLength();
        if (templateId > 0 && version > 0) {
            switch (templateId) {
                case 5001: //establishmentAsk (начало сессии)
                    EstablishmentAckDecoder establishmentAckDecoder = new EstablishmentAckDecoder();
                    establishmentAckDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    sequenceNum = establishmentAckDecoder.nextSeqNo();
                    if (heartBeatProcess == null) {
                        heartBeatProcess = new TwimeHeartBeatProcess(sequenceNum, outputChannel, intervalMsec);
                    }
                    new Thread(heartBeatProcess).start();
                    break;
                case 5006://heartbeat (sequence)
                    SequenceDecoder sequenceDecoder = new SequenceDecoder();
                    sequenceDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("sequence nextSeqNo: " + sequenceDecoder.nextSeqNo());
                    break;
                case 5003: //terminate
                    TerminateDecoder terminateDecoder = new TerminateDecoder();
                    terminateDecoder.wrap(unsafeBuffer, bytesOffset, blockLength, version);
                    System.out.println("terminate, reason: " + terminateDecoder.terminationCode().toString());
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


}
