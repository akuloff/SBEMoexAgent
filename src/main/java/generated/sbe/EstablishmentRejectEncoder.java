/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"EstablishmentRejectEncoder"})
@SuppressWarnings("all")
public class EstablishmentRejectEncoder
{
    public static final int BLOCK_LENGTH = 9;
    public static final int TEMPLATE_ID = 5002;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final EstablishmentRejectEncoder parentMessage = this;
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

    public EstablishmentRejectEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public static int requestTimestampEncodingOffset()
    {
        return 0;
    }

    public static int requestTimestampEncodingLength()
    {
        return 8;
    }

    public static long requestTimestampNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long requestTimestampMinValue()
    {
        return 0x0L;
    }

    public static long requestTimestampMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public EstablishmentRejectEncoder requestTimestamp(final long value)
    {
        buffer.putLong(offset + 0, value, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }


    public static int establishmentRejectCodeEncodingOffset()
    {
        return 8;
    }

    public static int establishmentRejectCodeEncodingLength()
    {
        return 1;
    }

    public EstablishmentRejectEncoder establishmentRejectCode(final EstablishmentRejectCodeEnum value)
    {
        buffer.putByte(offset + 8, (byte)value.value());
        return this;
    }


    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        EstablishmentRejectDecoder writer = new EstablishmentRejectDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
