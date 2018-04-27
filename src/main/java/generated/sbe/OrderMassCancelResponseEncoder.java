/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"OrderMassCancelResponseEncoder"})
@SuppressWarnings("all")
public class OrderMassCancelResponseEncoder
{
    public static final int BLOCK_LENGTH = 24;
    public static final int TEMPLATE_ID = 7007;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final OrderMassCancelResponseEncoder parentMessage = this;
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

    public OrderMassCancelResponseEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public OrderMassCancelResponseEncoder clOrdID(final long value)
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

    public OrderMassCancelResponseEncoder timestamp(final long value)
    {
        buffer.putLong(offset + 8, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int totalAffectedOrdersEncodingOffset()
    {
        return 16;
    }

    public static int totalAffectedOrdersEncodingLength()
    {
        return 4;
    }

    public static int totalAffectedOrdersNullValue()
    {
        return 2147483647;
    }

    public static int totalAffectedOrdersMinValue()
    {
        return -2147483648;
    }

    public static int totalAffectedOrdersMaxValue()
    {
        return 2147483646;
    }

    public OrderMassCancelResponseEncoder totalAffectedOrders(final int value)
    {
        buffer.putInt(offset + 16, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int ordRejReasonEncodingOffset()
    {
        return 20;
    }

    public static int ordRejReasonEncodingLength()
    {
        return 4;
    }

    public static int ordRejReasonNullValue()
    {
        return 2147483647;
    }

    public static int ordRejReasonMinValue()
    {
        return -2147483648;
    }

    public static int ordRejReasonMaxValue()
    {
        return 2147483646;
    }

    public OrderMassCancelResponseEncoder ordRejReason(final int value)
    {
        buffer.putInt(offset + 20, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }



    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        OrderMassCancelResponseDecoder writer = new OrderMassCancelResponseDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
