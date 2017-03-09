package SimpleIOSocket;

import org.agrona.DirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import sbe.EstablishmentAckDecoder;
import sbe.MessageHeaderDecoder;
import sbe.SequenceDecoder;
import sbe.TerminateDecoder;

import java.nio.channels.WritableByteChannel;

/**
 * Created by mpoke_000 on 08.03.2017.
 */
public class TwimeDecoder {
    private MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();
    private long sequenceNum = 0;
    private TwimeHeartBeatProcess heartBeatProcess = null;
    private long intervalMsec = 0;
    private WritableByteChannel outputChannel;

    public TwimeHeartBeatProcess getHeartBeatProcess() {
        return heartBeatProcess;
    }

    public long getIntervalMsec() {
        return intervalMsec;
    }

    public TwimeDecoder setIntervalMsec(long intervalMsec) {
        this.intervalMsec = intervalMsec;
        return this;
    }

    public WritableByteChannel getOutputChannel() {
        return outputChannel;
    }

    public TwimeDecoder setOutputChannel(WritableByteChannel outputChannel) {
        this.outputChannel = outputChannel;
        return this;
    }

    public void decodeMessage(UnsafeBuffer unsafeBuffer){
        int bytesOffset = 0;
        messageHeaderDecoder.wrap(unsafeBuffer, bytesOffset);

        int templateId = messageHeaderDecoder.templateId();
        int schemaId = messageHeaderDecoder.schemaId();
        int version = messageHeaderDecoder.version();
        int blockLength = messageHeaderDecoder.blockLength();

        System.out.println("TwimeDecoder, schemaId: " + schemaId + " |version: " + version + " |templateId: " + templateId + " |blockLength: " + blockLength);

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

}
