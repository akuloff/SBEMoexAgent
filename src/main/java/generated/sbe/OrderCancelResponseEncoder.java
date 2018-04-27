/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"OrderCancelResponseEncoder"})
@SuppressWarnings("all")
public class OrderCancelResponseEncoder
{
    public static final int BLOCK_LENGTH = 44;
    public static final int TEMPLATE_ID = 7003;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final OrderCancelResponseEncoder parentMessage = this;
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

    public OrderCancelResponseEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public OrderCancelResponseEncoder clOrdID(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int timestampEncodingOffset()
    {
        return 8;
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

    public OrderCancelResponseEncoder timestamp(final long value)
    {
        buffer.putLong(offset + 8, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int orderIDEncodingOffset()
    {
        return 16;
    }

    public static int orderIDEncodingLength()
    {
        return 8;
    }

    public static long orderIDNullValue()
    {
        return 9223372036854775807L;
    }

    public static long orderIDMinValue()
    {
        return -9223372036854775808L;
    }

    public static long orderIDMaxValue()
    {
        return 9223372036854775806L;
    }

    public OrderCancelResponseEncoder orderID(final long value)
    {
        buffer.putLong(offset + 16, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int flagsEncodingOffset()
    {
        return 24;
    }

    public static int flagsEncodingLength()
    {
        return 8;
    }

    private final FlagsSetEncoder flags = new FlagsSetEncoder();

    public FlagsSetEncoder flags()
    {
        flags.wrap(buffer, offset + 24);
        return flags;
    }

    public static int orderQtyEncodingOffset()
    {
        return 32;
    }

    public static int orderQtyEncodingLength()
    {
        return 4;
    }

    public static long orderQtyNullValue()
    {
        return 4294967295L;
    }

    public static long orderQtyMinValue()
    {
        return 0L;
    }

    public static long orderQtyMaxValue()
    {
        return 4294967294L;
    }

    public OrderCancelResponseEncoder orderQty(final long value)
    {
        buffer.putInt(offset + 32, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int tradingSessionIDEncodingOffset()
    {
        return 36;
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

    public OrderCancelResponseEncoder tradingSessionID(final int value)
    {
        buffer.putInt(offset + 36, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int clOrdLinkIDEncodingOffset()
    {
        return 40;
    }

    public static int clOrdLinkIDEncodingLength()
    {
        return 4;
    }

    public static int clOrdLinkIDNullValue()
    {
        return 2147483647;
    }

    public static int clOrdLinkIDMinValue()
    {
        return -2147483648;
    }

    public static int clOrdLinkIDMaxValue()
    {
        return 2147483646;
    }

    public OrderCancelResponseEncoder clOrdLinkID(final int value)
    {
        buffer.putInt(offset + 40, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }



    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        OrderCancelResponseDecoder writer = new OrderCancelResponseDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
