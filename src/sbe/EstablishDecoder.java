/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

import org.agrona.MutableDirectBuffer;
import org.agrona.DirectBuffer;

@javax.annotation.Generated(value = {"sbe.EstablishDecoder"})
@SuppressWarnings("all")
public class EstablishDecoder
{
    public static final int BLOCK_LENGTH = 32;
    public static final int TEMPLATE_ID = 5000;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final EstablishDecoder parentMessage = this;
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

    public EstablishDecoder wrap(
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

    public static int timestampId()
    {
        return 20204;
    }

    public static int timestampSinceVersion()
    {
        return 0;
    }

    public static int timestampEncodingOffset()
    {
        return 0;
    }

    public static int timestampEncodingLength()
    {
        return 8;
    }

    public static String timestampMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public long timestamp()
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


    public static int credentialsId()
    {
        return 20206;
    }

    public static int credentialsSinceVersion()
    {
        return 0;
    }

    public static int credentialsEncodingOffset()
    {
        return 12;
    }

    public static int credentialsEncodingLength()
    {
        return 20;
    }

    public static String credentialsMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public byte credentials(final int index)
    {
        if (index < 0 || index >= 20)
        {
            throw new IndexOutOfBoundsException("index out of range: index=" + index);
        }

        final int pos = this.offset + 12 + (index * 1);

        return buffer.getByte(pos);
    }


    public static String credentialsCharacterEncoding()
    {
        return "UTF-8";
    }

    public int getCredentials(final byte[] dst, final int dstOffset)
    {
        final int length = 20;
        if (dstOffset < 0 || dstOffset > (dst.length - length))
        {
            throw new IndexOutOfBoundsException("dstOffset out of range for copy: offset=" + dstOffset);
        }

        buffer.getBytes(this.offset + 12, dst, dstOffset, length);

        return length;
    }

    public String credentials()
    {
        final byte[] dst = new byte[20];
        buffer.getBytes(this.offset + 12, dst, 0, 20);

        int end = 0;
        for (; end < 20 && dst[end] != 0; ++end);

        return new String(dst, 0, end, java.nio.charset.StandardCharsets.UTF_8);
    }



    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        final int originalLimit = limit();
        limit(offset + actingBlockLength);
        builder.append("[Establish](sbeTemplateId=");
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
        //Token{signal=BEGIN_FIELD, name='Timestamp', referencedName='null', description='null', id=20204, version=0, deprecated=0, encodedLength=0, offset=0, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='TimeStamp', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=0, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=UINT64, byteOrder=LITTLE_ENDIAN, minValue=0, maxValue=-2, nullValue=-1, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("timestamp=");
        builder.append(timestamp());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='KeepaliveInterval', referencedName='null', description='null', id=20205, version=0, deprecated=0, encodedLength=0, offset=8, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='DeltaMillisecs', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=4, offset=8, componentTokenCount=1, encoding=Encoding{presence=REQUIRED, primitiveType=UINT32, byteOrder=LITTLE_ENDIAN, minValue=1000, maxValue=60000, nullValue=null, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("keepaliveInterval=");
        builder.append(keepaliveInterval());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='Credentials', referencedName='null', description='null', id=20206, version=0, deprecated=0, encodedLength=0, offset=12, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='String20', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=20, offset=12, componentTokenCount=1, encoding=Encoding{presence=REQUIRED, primitiveType=CHAR, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("credentials=");
        for (int i = 0; i < credentialsLength() && credentials(i) > 0; i++)
        {
            builder.append((char)credentials(i));
        }

        limit(originalLimit);

        return builder;
    }
}
