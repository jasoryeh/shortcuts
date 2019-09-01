package tk.jasonho.shortcuts.everything.util;

import lombok.Getter;

import java.time.Instant;

public class Interval {
    @Getter
    private final Unit unit;
    @Getter
    private final long amount;

    public Interval(Unit unit, long amount) {
        this.unit = unit;
        this.amount = amount;
    }

    public long getInMs() {
        return amount * unit.getDivisor();
    }

    public Instant addToInstant(Instant i) {
        return Instant.ofEpochMilli(i.toEpochMilli() + this.getInMs());
    }

    public boolean thisParamBiggerThanOrEqualToSelf(Interval i) {
        return Unit.isGreaterThanEqualTo(i.getUnit(), this.getUnit()) && ( i.getAmount() >= this.getAmount());
    }

    public double inUnit(Unit u) {
        return this.getInMs() / u.getDivisor();
    }
}
