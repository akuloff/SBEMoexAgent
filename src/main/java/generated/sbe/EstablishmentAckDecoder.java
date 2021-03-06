/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

import org.agrona.DirectBuffer;

@javax.annotation.Generated(value = {"EstablishmentAckDecoder"})
@SuppressWarnings("all")
public class EstablishmentAckDecoder
{
    public static final int BLOCK_LENGTH = 20;
    public static final int TEMPLATE_ID = 5001;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final EstablishmentAckDecoder parentMessage = this;
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

    public EstablishmentAckDecoder wrap(
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


    public static int keepaliveIntervalId()
    {
        return 20205;
    }

    public static int keepaliveIntervalSinceVersion()
    {
        return 0;
    }

    public static int keepaliveIntervalEncodingOffset()
    {
        return 8;
    }

    public static int keepaliveIntervalEncodingLength()
    {
        return 4;
    }

    public static String keepaliveIntervalMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public long keepaliveInterval()
    {
        return (buffer.getInt(offset + 8, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }


    public static int nextSeqNoId()
    {
        return 20208;
    }

    public static int nextSeqNoSinceVersion()
    {
        return 0;
    }

    public static int nextSeqNoEncodingOffset()
    {
        return 12;
    }

    public static int nextSeqNoEncodingLength()
    {
        return 8;
    }

    public static String nextSeqNoMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
    }

    public static long nextSeqNoNullValue()
    {
        return 0xffffffffffffffffL;
    }

    public static long nextSeqNoMinValue()
    {
        return 0x0L;
    }

    public static long nextSeqNoMaxValue()
    {
        return 0xfffffffffffffffeL;
    }

    public long nextSeqNo()
    {
        return buffer.getLong(offset + 12, java.nio.ByteOrder.LITTLE_ENDIAN);
    }



    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        final int originalLimit = limit();
        limit(offset + actingBlockLength);
        builder.append("[EstablishmentAck](sbeTemplateId=");
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
        //Token{signal=BEGIN_FIELD, name='KeepaliveInterval', referencedName='null', description='null', id=20205, version=0, deprecated=0, encodedLength=0, offset=8, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='DeltaMillisecs', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=4, offset=8, componentTokenCount=1, encoding=Encoding{presence=REQUIRED, primitiveType=UINT32, byteOrder=LITTLE_ENDIAN, minValue=1000, maxValue=60000, nullValue=null, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("keepaliveInterval=");
        builder.append(keepaliveInterval());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='NextSeqNo', referencedName='null', description='null', id=20208, version=0, deprecated=0, encodedLength=0, offset=12, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='UInt64', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=12, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=UINT64, byteOrder=LITTLE_ENDIAN, minValue=0, maxValue=-2, nullValue=-1, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("nextSeqNo=");
        builder.append(nextSeqNo());

        limit(originalLimit);

        return builder;
    }
}
