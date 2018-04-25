/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

@javax.annotation.Generated(value = {"sbe.SessionRejectReasonEnum"})
public enum SessionRejectReasonEnum
{
    ValueIsIncorrect((short)5),
    Other((short)99),
    SystemIsUnavailable((short)100),
    NULL_VAL((short)255);

    private final short value;

    SessionRejectReasonEnum(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static SessionRejectReasonEnum get(final short value)
    {
        switch (value)
        {
            case 5: return ValueIsIncorrect;
            case 99: return Other;
            case 100: return SystemIsUnavailable;
        }

        if ((short)255 == value)
        {
            return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
