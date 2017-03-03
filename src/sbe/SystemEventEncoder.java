/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

import org.agrona.MutableDirectBuffer;
import org.agrona.DirectBuffer;

@javax.annotation.Generated(value = {"sbe.SystemEventEncoder"})
@SuppressWarnings("all")
public class SystemEventEncoder
{
    public static final int BLOCK_LENGTH = 13;
    public static final int TEMPLATE_ID = 7011;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final SystemEventEncoder parentMessage = this;
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

    public SystemEventEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public static int timestampEncodingOffset()
    {
        return 0;
    }

    public static int timestampEncodingLength()
    {
        return 8;
    }

    public static long timestampNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long timestampMinValue()
    {
        return 0x0L;
    }

    public static long timestampMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public SystemEventEncoder timestamp(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int tradingSessionIDEncodingOffset()
    {
        return 8;
    }

    public static int tradingSessionIDEncodingLength()
    {
        return 4;
    }

    public static int tradingSessionIDNullValue()
    {
        return 2147483647;
    }

    public static int tradingSessionIDMinValue()
    {
        return -2147483648;
    }

    public static int tradingSessionIDMaxValue()
    {
        return 2147483646;
    }

    public SystemEventEncoder tradingSessionID(final int value)
    {
        buffer.putInt(offset + 8, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int tradSesEventEncodingOffset()
    {
        return 12;
    }

    public static int tradSesEventEncodingLength()
    {
        return 1;
    }

    public SystemEventEncoder tradSesEvent(final TradSesEventEnum value)
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
        SystemEventDecoder writer = new SystemEventDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
