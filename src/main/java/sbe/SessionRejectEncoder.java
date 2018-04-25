/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"sbe.SessionRejectEncoder"})
@SuppressWarnings("all")
public class SessionRejectEncoder
{
    public static final int BLOCK_LENGTH = 13;
    public static final int TEMPLATE_ID = 5008;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final SessionRejectEncoder parentMessage = this;
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

    public SessionRejectEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public SessionRejectEncoder clOrdID(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int refTagIDEncodingOffset()
    {
        return 8;
    }

    public static int refTagIDEncodingLength()
    {
        return 4;
    }

    public static long refTagIDNullValue()
    {
        return 4294967295L;
    }

    public static long refTagIDMinValue()
    {
        return 0L;
    }

    public static long refTagIDMaxValue()
    {
        return 4294967294L;
    }

    public SessionRejectEncoder refTagID(final long value)
    {
        buffer.putInt(offset + 8, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int sessionRejectReasonEncodingOffset()
    {
        return 12;
    }

    public static int sessionRejectReasonEncodingLength()
    {
        return 1;
    }

    public SessionRejectEncoder sessionRejectReason(final SessionRejectReasonEnum value)
    {
        buffer.putByte(offset + 12, (byte)value.value());
        return this;
    }


    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        SessionRejectDecoder writer = new SessionRejectDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
