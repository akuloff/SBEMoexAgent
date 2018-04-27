/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"TerminateEncoder"})
@SuppressWarnings("all")
public class TerminateEncoder
{
    public static final int BLOCK_LENGTH = 1;
    public static final int TEMPLATE_ID = 5003;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final TerminateEncoder parentMessage = this;
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

    public TerminateEncoder wrap(final MutableDirectBuffer buffer, final int offset)
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

    public static int terminationCodeEncodingOffset()
    {
        return 0;
    }

    public static int terminationCodeEncodingLength()
    {
        return 1;
    }

    public TerminateEncoder terminationCode(final TerminationCodeEnum value)
    {
        buffer.putByte(offset + 0, (byte)value.value());
        return this;
    }


    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        TerminateDecoder writer = new TerminateDecoder();
        writer.wrap(buffer, offset, BLOCK_LENGTH, SCHEMA_VERSION);

        return writer.appendTo(builder);
    }
}
