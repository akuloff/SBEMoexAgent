/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"sbe.OrderMassCancelRequestEncoder"})
@SuppressWarnings("all")
public class OrderMassCancelRequestEncoder
{
    public static final int BLOCK_LENGTH = 50;
    public static final int TEMPLATE_ID = 6004;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final OrderMassCancelRequestEncoder parentMessage = this;
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

    public OrderMassCancelRequestEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public OrderMassCancelRequestEncoder clOrdID(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int clOrdLinkIDEncodingOffset()
    {
        return 8;
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

    public OrderMassCancelRequestEncoder clOrdLinkID(final int value)
    {
        buffer.putInt(offset + 8, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int securityIDEncodingOffset()
    {
        return 12;
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

    public OrderMassCancelRequestEncoder securityID(final int value)
    {
        buffer.putInt(offset + 12, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int securityTypeEncodingOffset()
    {
        return 16;
    }

    public static int securityTypeEncodingLength()
    {
        return 1;
    }

    public OrderMassCancelRequestEncoder securityType(final SecurityTypeEnum value)
    {
        buffer.putByte(offset + 16, (byte)value.value());
        return this;
    }

    public static int sideEncodingOffset()
    {
        return 17;
    }

    public static int sideEncodingLength()
    {
        return 1;
    }

    public OrderMassCancelRequestEncoder side(final SideEnum value)
    {
        buffer.putByte(offset + 17, (byte)value.value());
        return this;
    }

    public static int accountEncodingOffset()
    {
        return 18;
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

        final int pos = this.offset + 18 + (index * 1);
        buffer.putByte(pos, value);
    }

    public static String accountCharacterEncoding()
    {
        return "UTF-8";
    }

    public OrderMassCancelRequestEncoder putAccount(final byte[] src, final int srcOffset)
    {
        final int length = 7;
        if (srcOffset < 0 || srcOffset > (src.length - length))
        {
            throw new IndexOutOfBoundsException("srcOffset out of range for copy: offset=" + srcOffset);
        }

        buffer.putBytes(this.offset + 18, src, srcOffset, length);

        return this;
    }

    public OrderMassCancelRequestEncoder account(final String src)
    {
        final int length = 7;
        final byte[] bytes = src.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        if (bytes.length > length)
        {
            throw new IndexOutOfBoundsException("String too large for copy: byte length=" + bytes.length);
        }

        buffer.putBytes(this.offset + 18, bytes, 0, bytes.length);

        for (int start = bytes.length; start < length; ++start)
        {
            buffer.putByte(this.offset + 18 + start, (byte)0);
        }

        return this;
    }

    public static int securityGroupEncodingOffset()
    {
        return 25;
    }

    public static int securityGroupEncodingLength()
    {
        return 25;
    }

    public static byte securityGroupNullValue()
    {
        return (byte)0;
    }

    public static byte securityGroupMinValue()
    {
        return (byte)32;
    }

    public static byte securityGroupMaxValue()
    {
        return (byte)126;
    }

    public static int securityGroupLength()
    {
        return 25;
    }

    public void securityGroup(final int index, final byte value)
    {
        if (index < 0 || index >= 25)
        {
            throw new IndexOutOfBoundsException("index out of range: index=" + index);
        }

        final int pos = this.offset + 25 + (index * 1);
        buffer.putByte(pos, value);
    }

    public static String securityGroupCharacterEncoding()
    {
        return "UTF-8";
    }

    public OrderMassCancelRequestEncoder putSecurityGroup(final byte[] src, final int srcOffset)
    {
        final int length = 25;
        if (srcOffset < 0 || srcOffset > (src.length - length))
        {
            throw new IndexOutOfBoundsException("srcOffset out of range for copy: offset=" + srcOffset);
        }

        buffer.putBytes(this.offset + 25, src, srcOffset, length);

        return this;
    }

    public OrderMassCancelRequestEncoder securityGroup(final String src)
    {
        final int length = 25;
        final byte[] bytes = src.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        if (bytes.length > length)
        {
            throw new IndexOutOfBoundsException("String too large for copy: byte length=" + bytes.length);
        }

        buffer.putBytes(this.offset + 25, bytes, 0, bytes.length);

        for (int start = bytes.length; start < length; ++start)
        {
            buffer.putByte(this.offset + 25 + start, (byte)0);
        }

        return this;
    }


    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        OrderMassCancelRequestDecoder writer = new OrderMassCancelRequestDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
