/* Generated SBE (Simple Binary Encoding) message codec */
package sbe;

import org.agrona.MutableDirectBuffer;

@javax.annotation.Generated(value = {"sbe.FlagsSetEncoder"})
@SuppressWarnings("all")
public class FlagsSetEncoder
{
    public static final int ENCODED_LENGTH = 8;
    private MutableDirectBuffer buffer;
    private int offset;

    public FlagsSetEncoder wrap(final MutableDirectBuffer buffer, final int offset)
    {
        this.buffer = buffer;
        this.offset = offset;

        return this;
    }

    public MutableDirectBuffer buffer()
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

    public FlagsSetEncoder clear()
    {
        buffer.putLong(offset, 0x0L, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder day(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 0) : bits & ~(1L << 0);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder iOC(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 1) : bits & ~(1L << 1);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder oTC(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 2) : bits & ~(1L << 2);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder posTransfer(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 3) : bits & ~(1L << 3);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder collateral(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 4) : bits & ~(1L << 4);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder optExercise(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 5) : bits & ~(1L << 5);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder expiration(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 7) : bits & ~(1L << 7);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder repo(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 17) : bits & ~(1L << 17);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder series(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 18) : bits & ~(1L << 18);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder fOK(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 19) : bits & ~(1L << 19);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder replace(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 20) : bits & ~(1L << 20);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder cancel(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 21) : bits & ~(1L << 21);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder massCancel(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 22) : bits & ~(1L << 22);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder optExp(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 23) : bits & ~(1L << 23);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder clearing(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 25) : bits & ~(1L << 25);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder negotiated(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 26) : bits & ~(1L << 26);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder multiLeg(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 27) : bits & ~(1L << 27);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder nonDelivery(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 28) : bits & ~(1L << 28);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder crossTrade(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 29) : bits & ~(1L << 29);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder futExercise(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 30) : bits & ~(1L << 30);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public FlagsSetEncoder cOD(final boolean value)
    {
        long bits = buffer.getLong(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        bits = value ? bits | (1L << 32) : bits & ~(1L << 32);
        buffer.putLong(offset, bits, java.nio.ByteOrder.LITTLE_ENDIAN);
        return this;
    }
}
