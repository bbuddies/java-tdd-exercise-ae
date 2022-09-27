package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;

public class Budget {
    private final YearMonth month;
    private final long amount;

    public Budget(YearMonth month, long amount) {
        this.month = month;
        this.amount = amount;
    }

    public YearMonth getMonth() {
        return month;
    }

    public long getAmount() {
        return amount;
    }

    LocalDate getEnd() {
        return month.atEndOfMonth();
    }

    LocalDate getStart() {
        return month.atDay(1);
    }

    Period getPeriod() {
        return new Period(getStart(), getEnd());
    }

    int getDayCount() {
        return getMonth().lengthOfMonth();
    }

    long getAmountOfOverlapping(Period period) {
        return amount / getDayCount() * period.getOverlappingDayCount(getPeriod());
    }
}
