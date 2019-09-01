package tk.jasonho.shortcuts.everything.util;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public enum Unit {
    MILLISECOND(1, "millisecond", "milliseconds", "ms"),
    SECOND(MILLISECOND.getDivisor() * 1000, "second", "seconds", "sec", "s"),
    MINUTE(SECOND.getDivisor() * 60, "minute", "minutes", "min", "m"),
    HOUR(MINUTE.getDivisor() * 60, "hour", "hours", "h"),
    DAY(HOUR.getDivisor() * 24, "day", "days", "d"),
    MONTH(DAY.getDivisor() * 28, "month", "months", "mo", "M"),
    YEAR(MONTH.getDivisor() * 12, "year", "years", "y");

    @Getter
    private final long divisor;
    @Getter
    private final String[] identifiers;

    Unit(long divisor, String... identifiers) {
        this.divisor = divisor;
        this.identifiers = identifiers;
    }

    /**
     * Searches for unit from the identifiers
     * @param id identifier to match
     * @return unit, null if not found
     */
    public static Unit forIdentifier(String id) {
        for (Unit value : Unit.values()) {
            for (String identifier : value.getIdentifiers()) {
                if(identifier.equals(id)) {
                    return value;
                }
            }
        }
        return null;
    }

    public static List<Interval> getIntervals(String intervals) throws IllegalArgumentException, NumberFormatException {
        String[] split = intervals.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        List<Interval> is = new ArrayList<>();

        Integer currentNum = null;
        Unit currentUnit = null;

        boolean number = true;
        for (int i = 0; i < split.length; i++) {
            if (number) {
                currentNum = Integer.parseInt(split[i]);
            } else {
                currentUnit = forIdentifier(split[i]);
                if(currentUnit == null) {
                    throw new IllegalArgumentException("Invalid unit: " + currentUnit);
                }
            }
            number = !number;

            if(currentNum != null && currentUnit != null) {
                is.add(new Interval(currentUnit, currentNum));
                currentNum = null;
                currentUnit = null;
            }
        }

        return orderIntervals(is);
    }

    public static boolean isGreaterThanEqualTo(Unit here, Unit biggerThanHere) {
        return here.getDivisor() >= biggerThanHere.getDivisor();
    }

    private static List<Interval> orderIntervals(List<Interval> o) {
        ArrayList<Interval> objects = new ArrayList<>();

        // start with smallest interval
        Interval test = new Interval(MILLISECOND, -1);
        while(objects.size() != o.size()) {
            for (Interval current : o) {
                if (test.thisParamBiggerThanOrEqualToSelf(current)) {
                    if (!objects.contains(current)) {
                        objects.add(current);
                    }
                }
            }
        }

        return objects;
    }
}
