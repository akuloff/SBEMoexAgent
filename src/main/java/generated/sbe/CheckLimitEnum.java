/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

@javax.annotation.Generated(value = {"CheckLimitEnum"})
public enum CheckLimitEnum
{
    DontCheck((short)0),
    Check((short)1),
    NULL_VAL((short)255);

    private final short value;

    CheckLimitEnum(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static CheckLimitEnum get(final short value)
    {
        switch (value)
        {
            case 0: return DontCheck;
            case 1: return Check;
        }

        if ((short)255 == value)
        {
            return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
