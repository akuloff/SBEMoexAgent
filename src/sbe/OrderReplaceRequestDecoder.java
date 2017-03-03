/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

import org.agrona.MutableDirectBuffer;
import org.agrona.DirectBuffer;

@javax.annotation.Generated(value = {"sbe.OrderReplaceRequestDecoder"})
@SuppressWarnings("all")
public class OrderReplaceRequestDecoder
{
    public static final int BLOCK_LENGTH = 41;
    public static final int TEMPLATE_ID = 6003;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final OrderReplaceRequestDecoder parentMessage = this;
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

    public OrderReplaceRequestDecoder wrap(
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

    public static int clOrdIDId()
    {
        return 11;
    }

    public static int clOrdIDSinceVersion()
    {
        return 0;
    }

    public static int clOrdIDEncodingOffset()
    {
        return 0;
    }

    public static int clOrdIDEncodingLength()
    {
        return 8;
    }

    public static String clOrdIDMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public long clOrdID()
    {
        return buffer.getLong(offset + 0, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int orderIDId()
    {
        return 37;
    }

    public static int orderIDSinceVersion()
    {
        return 0;
    }

    public static int orderIDEncodingOffset()
    {
        return 8;
    }

    public static int orderIDEncodingLength()
    {
        return 8;
    }

    public static String orderIDMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public long orderID()
    {
        return buffer.getLong(offset + 8, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int priceId()
    {
        return 44;
    }

    public static int priceSinceVersion()
    {
        return 0;
    }

    public static int priceEncodingOffset()
    {
        return 16;
    }

    public static int priceEncodingLength()
    {
        return 8;
    }

    public static String priceMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
    }

    private final Decimal5Decoder price = new Decimal5Decoder();

    public Decimal5Decoder price()
    {
        price.wrap(buffer, offset + 16);
        return price;
    }

    public static int orderQtyId()
    {
        return 38;
    }

    public static int orderQtySinceVersion()
    {
        return 0;
    }

    public static int orderQtyEncodingOffset()
    {
        return 24;
    }

    public static int orderQtyEncodingLength()
    {
        return 4;
    }

    public static String orderQtyMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public long orderQty()
    {
        return (buffer.getInt(offset + 24, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }


    public static int clOrdLinkIDId()
    {
        return 583;
    }

    public static int clOrdLinkIDSinceVersion()
    {
        return 0;
    }

    public static int clOrdLinkIDEncodingOffset()
    {
        return 28;
    }

    public static int clOrdLinkIDEncodingLength()
    {
        return 4;
    }

    public static String clOrdLinkIDMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public int clOrdLinkID()
    {
        return buffer.getInt(offset + 28, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int modeId()
    {
        return 20019;
    }

    public static int modeSinceVersion()
    {
        return 0;
    }

    public static int modeEncodingOffset()
    {
        return 32;
    }

    public static int modeEncodingLength()
    {
        return 1;
    }

    public static String modeMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
    }

    public ModeEnum mode()
    {
        return ModeEnum.get(((short)(buffer.getByte(offset + 32) & 0xFF)));
    }


    public static int checkLimitId()
    {
        return 20217;
    }

    public static int checkLimitSinceVersion()
    {
        return 0;
    }

    public static int checkLimitEncodingOffset()
    {
        return 33;
    }

    public static int checkLimitEncodingLength()
    {
        return 1;
    }

    public static String checkLimitMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
    }

    public CheckLimitEnum checkLimit()
    {
        return CheckLimitEnum.get(((short)(buffer.getByte(offset + 33) & 0xFF)));
    }


    public static int accountId()
    {
        return 1;
    }

    public static int accountSinceVersion()
    {
        return 0;
    }

    public static int accountEncodingOffset()
    {
        return 34;
    }

    public static int accountEncodingLength()
    {
        return 7;
    }

    public static String accountMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public byte account(final int index)
    {
        if (index < 0 || index >= 7)
        {
            throw new IndexOutOfBoundsException("index out of range: index=" + index);
        }

        final int pos = this.offset + 34 + (index * 1);

        return buffer.getByte(pos);
    }


    public static String accountCharacterEncoding()
    {
        return "UTF-8";
    }

    public int getAccount(final byte[] dst, final int dstOffset)
    {
        final int length = 7;
        if (dstOffset < 0 || dstOffset > (dst.length - length))
        {
            throw new IndexOutOfBoundsException("dstOffset out of range for copy: offset=" + dstOffset);
        }

        buffer.getBytes(this.offset + 34, dst, dstOffset, length);

        return length;
    }

    public String account()
    {
        final byte[] dst = new byte[7];
        buffer.getBytes(this.offset + 34, dst, 0, 7);

        int end = 0;
        for (; end < 7 && dst[end] != 0; ++end);

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
        builder.append("[OrderReplaceRequest](sbeTemplateId=");
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
        //Token{signal=BEGIN_FIELD, name='ClOrdID', referencedName='null', description='null', id=11, version=0, deprecated=0, encodedLength=0, offset=0, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='UInt64', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=0, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=UINT64, byteOrder=LITTLE_ENDIAN, minValue=0, maxValue=-2, nullValue=-1, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("clOrdID=");
        builder.append(clOrdID());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='OrderID', referencedName='null', description='null', id=37, version=0, deprecated=0, encodedLength=0, offset=8, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='Int64', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=8, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=INT64, byteOrder=LITTLE_ENDIAN, minValue=-9223372036854775808, maxValue=9223372036854775806, nullValue=9223372036854775807, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("orderID=");
        builder.append(orderID());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='Price', referencedName='null', description='null', id=44, version=0, deprecated=0, encodedLength=0, offset=16, componentTokenCount=6, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=BEGIN_COMPOSITE, name='Decimal5', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=16, componentTokenCount=4, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='null', timeUnit=null, semanticType='null'}}
        builder.append("price=");
        price().appendTo(builder);
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='OrderQty', referencedName='null', description='null', id=38, version=0, deprecated=0, encodedLength=0, offset=24, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='UInt32', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=4, offset=24, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=UINT32, byteOrder=LITTLE_ENDIAN, minValue=0, maxValue=4294967294, nullValue=4294967295, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("orderQty=");
        builder.append(orderQty());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='ClOrdLinkID', referencedName='null', description='null', id=583, version=0, deprecated=0, encodedLength=0, offset=28, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='Int32', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=4, offset=28, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=INT32, byteOrder=LITTLE_ENDIAN, minValue=-2147483648, maxValue=2147483646, nullValue=2147483647, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("clOrdLinkID=");
        builder.append(clOrdLinkID());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='Mode', referencedName='null', description='null', id=20019, version=0, deprecated=0, encodedLength=0, offset=32, componentTokenCount=8, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=BEGIN_ENUM, name='ModeEnum', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=1, offset=32, componentTokenCount=6, encoding=Encoding{presence=REQUIRED, primitiveType=UINT8, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='null', timeUnit=null, semanticType='null'}}
        builder.append("mode=");
        builder.append(mode());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='CheckLimit', referencedName='null', description='null', id=20217, version=0, deprecated=0, encodedLength=0, offset=33, componentTokenCount=6, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=BEGIN_ENUM, name='CheckLimitEnum', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=1, offset=33, componentTokenCount=4, encoding=Encoding{presence=REQUIRED, primitiveType=UINT8, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='null', timeUnit=null, semanticType='null'}}
        builder.append("checkLimit=");
        builder.append(checkLimit());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='Account', referencedName='null', description='null', id=1, version=0, deprecated=0, encodedLength=0, offset=34, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='String7', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=7, offset=34, componentTokenCount=1, encoding=Encoding{presence=REQUIRED, primitiveType=CHAR, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("account=");
        for (int i = 0; i < accountLength() && account(i) > 0; i++)
        {
            builder.append((char)account(i));
        }

        limit(originalLimit);

        return builder;
    }
}
