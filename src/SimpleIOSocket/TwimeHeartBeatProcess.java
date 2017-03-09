package SimpleIOSocket;

import org.agrona.concurrent.UnsafeBuffer;
import sbe.EstablishEncoder;
import sbe.MessageHeaderEncoder;
import sbe.SequenceEncoder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/**
 * Created by mpoke_000 on 09.03.2017.
 */
public class TwimeHeartBeatProcess implements Runnable{
    private boolean isStopped = false;
    private long sequenceNum = 0;
    private WritableByteChannel channel = null;
    private long intervalMsec = 0;
    private byte[] bArray = new byte[4096];

    MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
    SequenceEncoder sequenceEncoder = new SequenceEncoder();

    public TwimeHeartBeatProcess(long sequenceNum, WritableByteChannel channel, long intervalMsec) {
        this.sequenceNum = sequenceNum;
        this.channel = channel;
        this.intervalMsec = intervalMsec;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public TwimeHeartBeatProcess setStopped(boolean stopped) {
        isStopped = stopped;
        return this;
    }

    private void sendSequence(long seqNum){
        int bufferOffset = 0;
        int encodingLength = 0;

        ByteBuffer byteBuffer = ByteBuffer.wrap(bArray);
        UnsafeBuffer directBuffer = new UnsafeBuffer(byteBuffer);

        messageHeaderEncoder.wrap(directBuffer, bufferOffset)
                .blockLength(sequenceEncoder.sbeBlockLength())
                .templateId(sequenceEncoder.sbeTemplateId())
                .schemaId(sequenceEncoder.sbeSchemaId())
                .version(sequenceEncoder.sbeSchemaVersion());

        bufferOffset += messageHeaderEncoder.encodedLength();
        encodingLength += messageHeaderEncoder.encodedLength();

        sequenceEncoder.wrap(directBuffer, bufferOffset);
        sequenceEncoder.nextSeqNo(SequenceEncoder.nextSeqNoNullValue());
        //sequenceEncoder.wrap(directBuffer, bufferOffset).nextSeqNo(seqNum);
        encodingLength += sequenceEncoder.encodedLength();

        byteBuffer.limit(encodingLength);
        try {
            System.out.println(" >> TwimeHeartBeatProcess send sequence, seqNum: " + seqNum  + " |encodingLength: " + encodingLength + " .....");
            channel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
            this.setStopped(true);
        }
    }

    @Override
    public void run() {
        while(!isStopped){
            try {
                Thread.sleep(intervalMsec);
                if(!isStopped) {
                    sendSequence(sequenceNum);
                    //sequenceNum ++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                isStopped = true;
            }
        }
    }
}
