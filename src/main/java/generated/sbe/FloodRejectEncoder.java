/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"FloodRejectEncoder"})
@SuppressWarnings("all")
public class FloodRejectEncoder
{
    public static final int BLOCK_LENGTH = 16;
    public static final int TEMPLATE_ID = 5007;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final FloodRejectEncoder parentMessage = this;
    private MutableDirectBuffer buffer;
    protected int offset;
    protected int limit;

    public int sbeBlockLength()
    {
        return BLOCK_LENGTH;
    }

    public int sbeTemplateId()
    {
        return TEMPLATE_ID;
    }

    public int sbeSchemaId()
    {
        return SCHEMA_ID;
    }

    public int sbeSchemaVersion()
    {
        return SCHEMA_VERSION;
    }

    public String sbeSemanticType()
    {
        return "";
    }

    public MutableDirectBuffer buffer()
    {
        return buffer;
    }

    public int offset()
    {
        return offset;
    }

    public FloodRejectEncoder wrap(final MutableDirectBuffer buffer, final int offset)
    {
        this.buffer = buffer;
        this.offset = offset;
        limit(offset + BLOCK_LENGTH);

        return this;
    }

    public int encodedLength()
    {
        return limit - offset;
    }

    public int limit()
    {
        return limit;
    }

    public void limit(final int limit)
    {
        this.limit = limit;
    }

    public static int clOrdIDEncodingOffset()
    {
        return 0;
    }

    public static int clOrdIDEncodingLength()
    {
        return 8;
    }

    public static long clOrdIDNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long clOrdIDMinValue()
    {
        return 0x0L;
    }

    public static long clOrdIDMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public FloodRejectEncoder clOrdID(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int queueSizeEncodingOffset()
    {
        return 8;
    }

    public static int queueSizeEncodingLength()
    {
        return 4;
    }

    public static long queueSizeNullValue()
    {
        return 4294967295L;
    }

    public static long queueSizeMinValue()
    {
        return 0L;
    }

    public static long queueSizeMaxValue()
    {
        return 4294967294L;
    }

    public FloodRejectEncoder queueSize(final long value)
    {
        buffer.putInt(offset + 8, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int penaltyRemainEncodingOffset()
    {
        return 12;
    }

    public static int penaltyRemainEncodingLength()
    {
        return 4;
    }

    public static long penaltyRemainNullValue()
    {
        return 4294967295L;
    }

    public static long penaltyRemainMinValue()
    {
        return 0L;
    }

    public static long penaltyRemainMaxValue()
    {
        return 4294967294L;
    }

    public FloodRejectEncoder penaltyRemain(final long value)
    {
        buffer.putInt(offset + 12, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }



    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        FloodRejectDecoder writer = new FloodRejectDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
