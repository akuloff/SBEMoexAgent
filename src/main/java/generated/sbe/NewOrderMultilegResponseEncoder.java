/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"NewOrderMultilegResponseEncoder"})
@SuppressWarnings("all")
public class NewOrderMultilegResponseEncoder
{
    public static final int BLOCK_LENGTH = 65;
    public static final int TEMPLATE_ID = 7001;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final NewOrderMultilegResponseEncoder parentMessage = this;
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

    public NewOrderMultilegResponseEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public NewOrderMultilegResponseEncoder clOrdID(final long value)
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

    public NewOrderMultilegResponseEncoder timestamp(final long value)
    {
        buffer.putLong(offset + 8, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int expireDateEncodingOffset()
    {
        return 16;
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

    public NewOrderMultilegResponseEncoder expireDate(final long value)
    {
        buffer.putLong(offset + 16, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int orderIDEncodingOffset()
    {
        return 24;
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

    public NewOrderMultilegResponseEncoder orderID(final long value)
    {
        buffer.putLong(offset + 24, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int flagsEncodingOffset()
    {
        return 32;
    }

    public static int flagsEncodingLength()
    {
        return 8;
    }

    private final FlagsSetEncoder flags = new FlagsSetEncoder();

    public FlagsSetEncoder flags()
    {
        flags.wrap(buffer, offset + 32);
        return flags;
    }

    public static int priceEncodingOffset()
    {
        return 40;
    }

    public static int priceEncodingLength()
    {
        return 8;
    }

    private final Decimal5Encoder price = new Decimal5Encoder();

    public Decimal5Encoder price()
    {
        price.wrap(buffer, offset + 40);
        return price;
    }

    public static int securityIDEncodingOffset()
    {
        return 48;
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

    public NewOrderMultilegResponseEncoder securityID(final int value)
    {
        buffer.putInt(offset + 48, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int orderQtyEncodingOffset()
    {
        return 52;
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

    public NewOrderMultilegResponseEncoder orderQty(final long value)
    {
        buffer.putInt(offset + 52, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int tradingSessionIDEncodingOffset()
    {
        return 56;
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

    public NewOrderMultilegResponseEncoder tradingSessionID(final int value)
    {
        buffer.putInt(offset + 56, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int clOrdLinkIDEncodingOffset()
    {
        return 60;
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

    public NewOrderMultilegResponseEncoder clOrdLinkID(final int value)
    {
        buffer.putInt(offset + 60, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int sideEncodingOffset()
    {
        return 64;
    }

    public static int sideEncodingLength()
    {
        return 1;
    }

    public NewOrderMultilegResponseEncoder side(final SideEnum value)
    {
        buffer.putByte(offset + 64, (byte)value.value());
        return this;
    }


    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        NewOrderMultilegResponseDecoder writer = new NewOrderMultilegResponseDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
