/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

@javax.annotation.Generated(value = {"sbe.TradSesEventEnum"})
public enum TradSesEventEnum
{
    SessionDataReady((short)101),
    IntradayClearingFinished((short)102),
    IntradayClearingStarted((short)104),
    ClearingStarted((short)105),
    ExtensionOfLimitsFinished((short)106),
    BrokerRecalcFinished((short)108),
    NULL_VAL((short)255);

    private final short value;

    TradSesEventEnum(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static TradSesEventEnum get(final short value)
    {
        switch (value)
        {
            case 101: return SessionDataReady;
            case 102: return IntradayClearingFinished;
            case 104: return IntradayClearingStarted;
            case 105: return ClearingStarted;
            case 106: return ExtensionOfLimitsFinished;
            case 108: return BrokerRecalcFinished;
        }

        if ((short)255 == value)
        {
            return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
