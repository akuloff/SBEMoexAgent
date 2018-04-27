/* Generated SBE (Simple Binary Encoding) message codec */
package generated.sbe;

@javax.annotation.Generated(value = {"EstablishmentRejectCodeEnum"})
public enum EstablishmentRejectCodeEnum
{
    Unnegotiated((short)0),
    AlreadyEstablished((short)1),
    SessionBlocked((short)2),
    KeepaliveInterval((short)3),
    Credentials((short)4),
    Unspecified((short)5),
    NULL_VAL((short)255);

    private final short value;

    EstablishmentRejectCodeEnum(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static EstablishmentRejectCodeEnum get(final short value)
    {
        switch (value)
        {
            case 0: return Unnegotiated;
            case 1: return AlreadyEstablished;
            case 2: return SessionBlocked;
            case 3: return KeepaliveInterval;
            case 4: return Credentials;
            case 5: return Unspecified;
        }

        if ((short)255 == value)
        {
            return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
