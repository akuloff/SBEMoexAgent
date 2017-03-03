/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

@javax.annotation.Generated(value = {"sbe.TerminationCodeEnum"})
public enum TerminationCodeEnum
{
    Finished((short)0),
    UnspecifiedError((short)1),
    ReRequestOutOfBounds((short)2),
    ReRequestInProgress((short)3),
    TooFastClient((short)4),
    TooSlowClient((short)5),
    MissedHeartbeat((short)6),
    InvalidMessage((short)7),
    TCPFailure((short)8),
    InvalidSequenceNumber((short)9),
    ServerShutdown((short)10),
    NULL_VAL((short)255);

    private final short value;

    TerminationCodeEnum(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static TerminationCodeEnum get(final short value)
    {
        switch (value)
        {
            case 0: return Finished;
            case 1: return UnspecifiedError;
            case 2: return ReRequestOutOfBounds;
            case 3: return ReRequestInProgress;
            case 4: return TooFastClient;
            case 5: return TooSlowClient;
            case 6: return MissedHeartbeat;
            case 7: return InvalidMessage;
            case 8: return TCPFailure;
            case 9: return InvalidSequenceNumber;
            case 10: return ServerShutdown;
        }

        if ((short)255 == value)
        {
            return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
