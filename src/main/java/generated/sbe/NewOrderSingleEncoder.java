/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"NewOrderSingleEncoder"})
@SuppressWarnings("all")
public class NewOrderSingleEncoder
{
    public static final int BLOCK_LENGTH = 46;
    public static final int TEMPLATE_ID = 6000;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final NewOrderSingleEncoder parentMessage = this;
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

    public NewOrderSingleEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public NewOrderSingleEncoder clOrdID(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int expireDateEncodingOffset()
    {
        return 8;
    }

    public static int expireDateEncodingLength()
    {
        return 8;
    }

    public static long expireDateNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long expireDateMinValue()
    {
        return 0x0L;
    }

    public static long expireDateMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public NewOrderSingleEncoder expireDate(final long value)
    {
        buffer.putLong(offset + 8, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int priceEncodingOffset()
    {
        return 16;
    }

    public static int priceEncodingLength()
    {
        return 8;
    }

    private final Decimal5Encoder price = new Decimal5Encoder();

    public Decimal5Encoder price()
    {
        price.wrap(buffer, offset + 16);
        return price;
    }

    public static int securityIDEncodingOffset()
    {
        return 24;
    }

    public static int securityIDEncodingLength()
    {
        return 4;
    }

    public static int securityIDNullValue()
    {
        return 2147483647;
    }

    public static int securityIDMinValue()
    {
        return -2147483648;
    }

    public static int securityIDMaxValue()
    {
        return 2147483646;
    }

    public NewOrderSingleEncoder securityID(final int value)
    {
        buffer.putInt(offset + 24, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int clOrdLinkIDEncodingOffset()
    {
        return 28;
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

    public NewOrderSingleEncoder clOrdLinkID(final int value)
    {
        buffer.putInt(offset + 28, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
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

    public NewOrderSingleEncoder orderQty(final long value)
    {
        buffer.putInt(offset + 32, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int timeInForceEncodingOffset()
    {
        return 36;
    }

    public static int timeInForceEncodingLength()
    {
        return 1;
    }

    public NewOrderSingleEncoder timeInForce(final TimeInForceEnum value)
    {
        buffer.putByte(offset + 36, (byte)value.value());
        return this;
    }

    public static int sideEncodingOffset()
    {
        return 37;
    }

    public static int sideEncodingLength()
    {
        return 1;
    }

    public NewOrderSingleEncoder side(final SideEnum value)
    {
        buffer.putByte(offset + 37, (byte)value.value());
        return this;
    }

    public static int checkLimitEncodingOffset()
    {
        return 38;
    }

    public static int checkLimitEncodingLength()
    {
        return 1;
    }

    public NewOrderSingleEncoder checkLimit(final CheckLimitEnum value)
    {
        buffer.putByte(offset + 38, (byte)value.value());
        return this;
    }

    public static int accountEncodingOffset()
    {
        return 39;
    }

    public static int accountEncodingLength()
    {
        return 7;
    }

    public static byte accountNullValue()
    {
        return (byte)0;
    }

    public static byte accountMinValue()
    {
        return (byte)32;
    }

    public static byte accountMaxValue()
    {
        return (byte)126;
    }

    public static int accountLength()
    {
        return 7;
    }

    public void account(final int index, final byte value)
    {
        if (index < 0 || index >= 7)
        {
            throw new IndexOutOfBoundsException("index out of range: index=" + index);
        }

        final int pos = this.offset + 39 + (index * 1);
        buffer.putByte(pos, value);
    }

    public static String accountCharacterEncoding()
    {
        return "UTF-8";
    }

    public NewOrderSingleEncoder putAccount(final byte[] src, final int srcOffset)
    {
        final int length = 7;
        if (srcOffset < 0 || srcOffset > (src.length - length))
        {
            throw new IndexOutOfBoundsException("srcOffset out of range for copy: offset=" + srcOffset);
        }

        buffer.putBytes(this.offset + 39, src, srcOffset, length);

        return this;
    }

    public NewOrderSingleEncoder account(final String src)
    {
        final int length = 7;
        final byte[] bytes = src.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        if (bytes.length > length)
        {
            throw new IndexOutOfBoundsException("String too large for copy: byte length=" + bytes.length);
        }

        buffer.putBytes(this.offset + 39, bytes, 0, bytes.length);

        for (int start = bytes.length; start < length; ++start)
        {
            buffer.putByte(this.offset + 39 + start, (byte)0);
        }

        return this;
    }


    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        NewOrderSingleDecoder writer = new NewOrderSingleDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
