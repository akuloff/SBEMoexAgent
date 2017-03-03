/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

import org.agrona.MutableDirectBuffer;
import org.agrona.DirectBuffer;

@javax.annotation.Generated(value = {"sbe.EstablishmentRejectDecoder"})
@SuppressWarnings("all")
public class EstablishmentRejectDecoder
{
    public static final int BLOCK_LENGTH = 9;
    public static final int TEMPLATE_ID = 5002;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final EstablishmentRejectDecoder parentMessage = this;
    private DirectBuffer buffer;
    protected int offset;
    protected int limit;
    protected int actingBlockLength;
    protected int actingVersion;

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

    public DirectBuffer buffer()
    {
        return buffer;
    }

    public int offset()
    {
        return offset;
    }

    public EstablishmentRejectDecoder wrap(
        final DirectBuffer buffer, final int offset, final int actingBlockLength, final int actingVersion)
    {
        this.buffer = buffer;
        this.offset = offset;
        this.actingBlockLength = actingBlockLength;
        this.actingVersion = actingVersion;
        limit(offset + actingBlockLength);

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

    public static int requestTimestampId()
    {
        return 20207;
    }

    public static int requestTimestampSinceVersion()
    {
        return 0;
    }

    public static int requestTimestampEncodingOffset()
    {
        return 0;
    }

    public static int requestTimestampEncodingLength()
    {
        return 8;
    }

    public static String requestTimestampMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public long requestTimestamp()
    {
        return buffer.getLong(offset + 0, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int establishmentRejectCodeId()
    {
        return 20209;
    }

    public static int establishmentRejectCodeSinceVersion()
    {
        return 0;
    }

    public static int establishmentRejectCodeEncodingOffset()
    {
        return 8;
    }

    public static int establishmentRejectCodeEncodingLength()
    {
        return 1;
    }

    public static String establishmentRejectCodeMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
    }

    public EstablishmentRejectCodeEnum establishmentRejectCode()
    {
        return EstablishmentRejectCodeEnum.get(((short)(buffer.getByte(offset + 8) & 0xFF)));
    }



    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        final int originalLimit = limit();
        limit(offset + actingBlockLength);
        builder.append("[EstablishmentReject](sbeTemplateId=");
        builder.append(TEMPLATE_ID);
        builder.append("|sbeSchemaId=");
        builder.append(SCHEMA_ID);
        builder.append("|sbeSchemaVersion=");
        if (parentMessage.actingVersion != SCHEMA_VERSION)
        {
            builder.append(parentMessage.actingVersion);
            builder.append('/');
        }
        builder.append(SCHEMA_VERSION);
        builder.append("|sbeBlockLength=");
        if (actingBlockLength != BLOCK_LENGTH)
        {
            builder.append(actingBlockLength);
            builder.append('/');
        }
        builder.append(BLOCK_LENGTH);
        builder.append("):");
        //Token{signal=BEGIN_FIELD, name='RequestTimestamp', referencedName='null', description='null', id=20207, version=0, deprecated=0, encodedLength=0, offset=0, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='TimeStamp', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=0, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=UINT64, byteOrder=LITTLE_ENDIAN, minValue=0, maxValue=-2, nullValue=-1, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("requestTimestamp=");
        builder.append(requestTimestamp());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='EstablishmentRejectCode', referencedName='null', description='null', id=20209, version=0, deprecated=0, encodedLength=0, offset=8, componentTokenCount=10, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=BEGIN_ENUM, name='EstablishmentRejectCodeEnum', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=1, offset=8, componentTokenCount=8, encoding=Encoding{presence=REQUIRED, primitiveType=UINT8, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='null', timeUnit=null, semanticType='null'}}
        builder.append("establishmentRejectCode=");
        builder.append(establishmentRejectCode());

        limit(originalLimit);

        return builder;
    }
}
