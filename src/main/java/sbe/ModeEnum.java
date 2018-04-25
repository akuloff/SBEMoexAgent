/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

@javax.annotation.Generated(value = {"sbe.ModeEnum"})
public enum ModeEnum
{
    DontChangeOrderQty((short)0),
    ChangeOrderQty((short)1),
    CheckOrderQtyAndCancelOrder((short)2),
    FixStyleReplace((short)3),
    NULL_VAL((short)255);

    private final short value;

    ModeEnum(final short value)
    {
        this.value = value;
    }

    public short value()
    {
        return value;
    }

    public static ModeEnum get(final short value)
    {
        switch (value)
        {
            case 0: return DontChangeOrderQty;
            case 1: return ChangeOrderQty;
            case 2: return CheckOrderQtyAndCancelOrder;
            case 3: return FixStyleReplace;
        }

        if ((short)255 == value)
        {
            return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
