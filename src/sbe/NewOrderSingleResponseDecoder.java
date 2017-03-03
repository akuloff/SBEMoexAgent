/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

import org.agrona.MutableDirectBuffer;
import org.agrona.DirectBuffer;

@javax.annotation.Generated(value = {"sbe.NewOrderSingleResponseDecoder"})
@SuppressWarnings("all")
public class NewOrderSingleResponseDecoder
{
    public static final int BLOCK_LENGTH = 65;
    public static final int TEMPLATE_ID = 7000;
    public static final int SCHEMA_ID = 19781;
    public static final int SCHEMA_VERSION = 1;

    private final NewOrderSingleResponseDecoder parentMessage = this;
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

    public NewOrderSingleResponseDecoder wrap(
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
        return 8;
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
        return buffer.getLong(offset + 8, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int expireDateId()
    {
        return 432;
    }

    public static int expireDateSinceVersion()
    {
        return 0;
    }

    public static int expireDateEncodingOffset()
    {
        return 16;
    }

    public static int expireDateEncodingLength()
    {
        return 8;
    }

    public static String expireDateMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public long expireDate()
    {
        return buffer.getLong(offset + 16, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return 24;
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
        return buffer.getLong(offset + 24, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int flagsId()
    {
        return 20215;
    }

    public static int flagsSinceVersion()
    {
        return 0;
    }

    public static int flagsEncodingOffset()
    {
        return 32;
    }

    public static int flagsEncodingLength()
    {
        return 8;
    }

    public static String flagsMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
    }

    private final FlagsSetDecoder flags = new FlagsSetDecoder();

    public FlagsSetDecoder flags()
    {
        flags.wrap(buffer, offset + 32);
        return flags;
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
        return 40;
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
        price.wrap(buffer, offset + 40);
        return price;
    }

    public static int securityIDId()
    {
        return 48;
    }

    public static int securityIDSinceVersion()
    {
        return 0;
    }

    public static int securityIDEncodingOffset()
    {
        return 48;
    }

    public static int securityIDEncodingLength()
    {
        return 4;
    }

    public static String securityIDMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public int securityID()
    {
        return buffer.getInt(offset + 48, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return 52;
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
        return (buffer.getInt(offset + 52, java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF_FFFFL);
    }


    public static int tradingSessionIDId()
    {
        return 336;
    }

    public static int tradingSessionIDSinceVersion()
    {
        return 0;
    }

    public static int tradingSessionIDEncodingOffset()
    {
        return 56;
    }

    public static int tradingSessionIDEncodingLength()
    {
        return 4;
    }

    public static String tradingSessionIDMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
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

    public int tradingSessionID()
    {
        return buffer.getInt(offset + 56, java.nio.ByteOrder.LITTLE_ENDIAN);
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
        return 60;
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
        return buffer.getInt(offset + 60, java.nio.ByteOrder.LITTLE_ENDIAN);
    }


    public static int sideId()
    {
        return 54;
    }

    public static int sideSinceVersion()
    {
        return 0;
    }

    public static int sideEncodingOffset()
    {
        return 64;
    }

    public static int sideEncodingLength()
    {
        return 1;
    }

    public static String sideMetaAttribute(final MetaAttribute metaAttribute)
    {
        switch (metaAttribute)
        {
            case EPOCH: return "unix";
            case TIME_UNIT: return "nanosecond";
            case SEMANTIC_TYPE: return "";
        }

        return "";
    }

    public SideEnum side()
    {
        return SideEnum.get(((short)(buffer.getByte(offset + 64) & 0xFF)));
    }



    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        final int originalLimit = limit();
        limit(offset + actingBlockLength);
        builder.append("[NewOrderSingleResponse](sbeTemplateId=");
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
        //Token{signal=BEGIN_FIELD, name='Timestamp', referencedName='null', description='null', id=20204, version=0, deprecated=0, encodedLength=0, offset=8, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='TimeStamp', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=8, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=UINT64, byteOrder=LITTLE_ENDIAN, minValue=0, maxValue=-2, nullValue=-1, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("timestamp=");
        builder.append(timestamp());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='ExpireDate', referencedName='null', description='null', id=432, version=0, deprecated=0, encodedLength=0, offset=16, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='TimeStamp', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=16, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=UINT64, byteOrder=LITTLE_ENDIAN, minValue=0, maxValue=-2, nullValue=-1, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("expireDate=");
        builder.append(expireDate());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='OrderID', referencedName='null', description='null', id=37, version=0, deprecated=0, encodedLength=0, offset=24, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='Int64', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=24, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=INT64, byteOrder=LITTLE_ENDIAN, minValue=-9223372036854775808, maxValue=9223372036854775806, nullValue=9223372036854775807, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("orderID=");
        builder.append(orderID());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='Flags', referencedName='null', description='null', id=20215, version=0, deprecated=0, encodedLength=0, offset=32, componentTokenCount=25, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=BEGIN_SET, name='FlagsSet', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=32, componentTokenCount=23, encoding=Encoding{presence=REQUIRED, primitiveType=UINT64, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='null', timeUnit=null, semanticType='null'}}
        builder.append("flags=");
        builder.append(flags());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='Price', referencedName='null', description='null', id=44, version=0, deprecated=0, encodedLength=0, offset=40, componentTokenCount=6, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=BEGIN_COMPOSITE, name='Decimal5', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=8, offset=40, componentTokenCount=4, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='null', timeUnit=null, semanticType='null'}}
        builder.append("price=");
        price().appendTo(builder);
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='SecurityID', referencedName='null', description='null', id=48, version=0, deprecated=0, encodedLength=0, offset=48, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='Int32', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=4, offset=48, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=INT32, byteOrder=LITTLE_ENDIAN, minValue=-2147483648, maxValue=2147483646, nullValue=2147483647, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("securityID=");
        builder.append(securityID());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='OrderQty', referencedName='null', description='null', id=38, version=0, deprecated=0, encodedLength=0, offset=52, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='UInt32', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=4, offset=52, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=UINT32, byteOrder=LITTLE_ENDIAN, minValue=0, maxValue=4294967294, nullValue=4294967295, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("orderQty=");
        builder.append(orderQty());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='TradingSessionID', referencedName='null', description='null', id=336, version=0, deprecated=0, encodedLength=0, offset=56, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='Int32', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=4, offset=56, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=INT32, byteOrder=LITTLE_ENDIAN, minValue=-2147483648, maxValue=2147483646, nullValue=2147483647, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("tradingSessionID=");
        builder.append(tradingSessionID());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='ClOrdLinkID', referencedName='null', description='null', id=583, version=0, deprecated=0, encodedLength=0, offset=60, componentTokenCount=3, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=ENCODING, name='Int32', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=4, offset=60, componentTokenCount=1, encoding=Encoding{presence=OPTIONAL, primitiveType=INT32, byteOrder=LITTLE_ENDIAN, minValue=-2147483648, maxValue=2147483646, nullValue=2147483647, constValue=null, characterEncoding='UTF-8', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        builder.append("clOrdLinkID=");
        builder.append(clOrdLinkID());
        builder.append('|');
        //Token{signal=BEGIN_FIELD, name='Side', referencedName='null', description='null', id=54, version=0, deprecated=0, encodedLength=0, offset=64, componentTokenCount=7, encoding=Encoding{presence=REQUIRED, primitiveType=null, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='unix', timeUnit=nanosecond, semanticType='null'}}
        //Token{signal=BEGIN_ENUM, name='SideEnum', referencedName='null', description='null', id=-1, version=0, deprecated=0, encodedLength=1, offset=64, componentTokenCount=5, encoding=Encoding{presence=REQUIRED, primitiveType=UINT8, byteOrder=LITTLE_ENDIAN, minValue=null, maxValue=null, nullValue=null, constValue=null, characterEncoding='null', epoch='null', timeUnit=null, semanticType='null'}}
        builder.append("side=");
        builder.append(side());

        limit(originalLimit);

        return builder;
    }
}
