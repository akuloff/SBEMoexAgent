/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

@javax.annotation.Generated(value = {"SecurityTypeEnum"})
public enum SecurityTypeEnum
{
    Future((short)0),
    Option((short)1),
    NULL_VAL((short)255);

    private final short value;

    SecurityTypeEnum(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static SecurityTypeEnum get(final short value)
    {
        switch (value)
        {
            case 0: return Future;
            case 1: return Option;
        }

        if ((short)255 == value)
        {
            return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
