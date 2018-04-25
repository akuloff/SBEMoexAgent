/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

import org.agrona.DirectBuffer;

@javax.annotation.Generated(value = {"sbe.FlagsSetDecoder"})
@SuppressWarnings("all")
public class FlagsSetDecoder
{
    public static final int ENCODED_LENGTH = 8;
    private DirectBuffer buffer;
    private int offset;

    public FlagsSetDecoder wrap(final DirectBuffer buffer, final int offset)
    {
        this.buffer = buffer;
        this.offset = offset;

        return this;
    }

    public DirectBuffer buffer()
    {
        return buffer;
    }

    public int offset()
    {
        return offset;
    }

    public int encodedLength()
    {
        return ENCODED_LENGTH;
    }

    public boolean day()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 0));
    }

    public boolean iOC()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 1));
    }

    public boolean oTC()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 2));
    }

    public boolean posTransfer()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 3));
    }

    public boolean collateral()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 4));
    }

    public boolean optExercise()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 5));
    }

    public boolean expiration()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 7));
    }

    public boolean repo()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 17));
    }

    public boolean series()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 18));
    }

    public boolean fOK()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 19));
    }

    public boolean replace()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 20));
    }

    public boolean cancel()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 21));
    }

    public boolean massCancel()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 22));
    }

    public boolean optExp()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 23));
    }

    public boolean clearing()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 25));
    }

    public boolean negotiated()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 26));
    }

    public boolean multiLeg()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 27));
    }

    public boolean nonDelivery()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 28));
    }

    public boolean crossTrade()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 29));
    }

    public boolean futExercise()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 30));
    }

    public boolean cOD()
    {
        return 0 != (buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN) & (1L << 32));
    }

    public String toString()
    {
        return appendTo(new StringBuilder(100)).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        builder.append('{');
        boolean atLeastOne = false;
        if (day())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("day");
            atLeastOne = true;
        }
        if (iOC())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("iOC");
            atLeastOne = true;
        }
        if (oTC())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("oTC");
            atLeastOne = true;
        }
        if (posTransfer())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("posTransfer");
            atLeastOne = true;
        }
        if (collateral())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("collateral");
            atLeastOne = true;
        }
        if (optExercise())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("optExercise");
            atLeastOne = true;
        }
        if (expiration())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("expiration");
            atLeastOne = true;
        }
        if (repo())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("repo");
            atLeastOne = true;
        }
        if (series())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("series");
            atLeastOne = true;
        }
        if (fOK())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("fOK");
            atLeastOne = true;
        }
        if (replace())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("replace");
            atLeastOne = true;
        }
        if (cancel())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("cancel");
            atLeastOne = true;
        }
        if (massCancel())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("massCancel");
            atLeastOne = true;
        }
        if (optExp())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("optExp");
            atLeastOne = true;
        }
        if (clearing())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("clearing");
            atLeastOne = true;
        }
        if (negotiated())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("negotiated");
            atLeastOne = true;
        }
        if (multiLeg())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("multiLeg");
            atLeastOne = true;
        }
        if (nonDelivery())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("nonDelivery");
            atLeastOne = true;
        }
        if (crossTrade())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("crossTrade");
            atLeastOne = true;
        }
        if (futExercise())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("futExercise");
            atLeastOne = true;
        }
        if (cOD())
        {
            if (atLeastOne)
            {
                builder.append(',');
            }
            builder.append("cOD");
            atLeastOne = true;
        }
        builder.append('}');

        return builder;
    }
}
