/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"EstablishEncoder"})
@SuppressWarnings("all")
public class EstablishEncoder
{
    public static final int BLOCK_LENGTH = 32;
    public static final int TEMPLATE_ID = 5000;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final EstablishEncoder parentMessage = this;
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

    public EstablishEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public EstablishEncoder timestamp(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int keepaliveIntervalEncodingOffset()
    {
        return 8;
    }

    public static int keepaliveIntervalEncodingLength()
    {
        return 4;
    }

    public static long keepaliveIntervalNullValue()
    {
        return 4294967294L;
    }

    public static long keepaliveIntervalMinValue()
    {
        return 1000L;
    }

    public static long keepaliveIntervalMaxValue()
    {
        return 60000L;
    }

    public EstablishEncoder keepaliveInterval(final long value)
    {
        buffer.putInt(offset + 8, (int)value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int credentialsEncodingOffset()
    {
        return 12;
    }

    public static int credentialsEncodingLength()
    {
        return 20;
    }

    public static byte credentialsNullValue()
    {
        return (byte)0;
    }

    public static byte credentialsMinValue()
    {
        return (byte)32;
    }

    public static byte credentialsMaxValue()
    {
        return (byte)126;
    }

    public static int credentialsLength()
    {
        return 20;
    }

    public void credentials(final int index, final byte value)
    {
        if (index < 0 || index >= 20)
        {
            throw new IndexOutOfBoundsException("index out of range: index=" + index);
        }

        final int pos = this.offset + 12 + (index * 1);
        buffer.putByte(pos, value);
    }

    public static String credentialsCharacterEncoding()
    {
        return "UTF-8";
    }

    public EstablishEncoder putCredentials(final byte[] src, final int srcOffset)
    {
        final int length = 20;
        if (srcOffset < 0 || srcOffset > (src.length - length))
        {
            throw new IndexOutOfBoundsException("srcOffset out of range for copy: offset=" + srcOffset);
        }

        buffer.putBytes(this.offset + 12, src, srcOffset, length);

        return this;
    }

    public EstablishEncoder credentials(final String src)
    {
        final int length = 20;
        final byte[] bytes = src.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        if (bytes.length > length)
        {
            throw new IndexOutOfBoundsException("String too large for copy: byte length=" + bytes.length);
        }

        buffer.putBytes(this.offset + 12, bytes, 0, bytes.length);

        for (int start = bytes.length; start < length; ++start)
        {
            buffer.putByte(this.offset + 12 + start, (byte)0);
        }

        return this;
    }


    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        EstablishDecoder writer = new EstablishDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
