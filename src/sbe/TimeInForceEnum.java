/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

@javax.annotation.Generated(value = {"sbe.TimeInForceEnum"})
public enum TimeInForceEnum
{
    Day((short)0),
    IOC((short)3),
    FOK((short)4),
    GTD((short)6),
    NULL_VAL((short)255);

    private final short value;

    TimeInForceEnum(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static TimeInForceEnum get(final short value)
    {
        switch (value)
        {
            case 0: return Day;
            case 3: return IOC;
            case 4: return FOK;
            case 6: return GTD;
        }

        if ((short)255 == value)
        {
            return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
