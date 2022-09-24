package com.odde.tdd;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class BudgetCalculate {
    List<Budget> budgetList;

    public BudgetCalculate() {
        budgetList = new ArrayList<>();
        budgetList.add(new Budget(YearMonth.of(2022, 9), 3000));
        budgetList.add(new Budget(YearMonth.of(2022, 10), 310));
        budgetList.add(new Budget(YearMonth.of(2022, 11), 60));
        budgetList.add(new Budget(YearMonth.of(2022, 12), 31));
        budgetList.add(new Budget(YearMonth.of(2023, 1), 3100));
    }

    public Integer getBudget (LocalDate start, LocalDate end) {
        if (null == start || null == end || start.isAfter(end)) {
            return 0;
        }

        // 区间预算总值
        long budget = 0;

        // 开始日期年月
        YearMonth s = YearMonth.of(start.getYear(), start.getMonthValue());
        // 结束日期年月
        YearMonth e = YearMonth.of(end.getYear(), end.getMonthValue());

        if (s.equals(e)) {
            // 同月
            for (Budget b : budgetList) {
                if (b.getMonth().equals(s)) {
                    LocalDate lastDay = start.with(TemporalAdjusters.lastDayOfMonth());
                    // 预算范围：开始日期 -> 结束日期
                    budget = budget + b.getAmount()/lastDay.getDayOfMonth()*(end.getDayOfMonth()-start.getDayOfMonth()+1);
                }
            }
        } else {
            // 不同月
            for (Budget b : budgetList) {
                if (b.getMonth().isAfter(s) && b.getMonth().isBefore(e)) {
                    // 范围内的月
                    budget += b.getAmount();
                } else if (b.getMonth().equals(s)) {
                    // 临界月份（开始）
                    LocalDate lastDay = start.with(TemporalAdjusters.lastDayOfMonth());
                    // 预算范围：开始日期 -> 开始日期当月最后一天
                    budget = budget + (b.getAmount()/lastDay.getDayOfMonth())*(lastDay.getDayOfMonth()-start.getDayOfMonth()+1);
                } else if (b.getMonth().equals(e)) {
                    // 临界月份（结束）
                    LocalDate lastDay = end.with(TemporalAdjusters.lastDayOfMonth());
                    // 预算范围：结束日期当月第一天 -> 结束日期
                    budget = budget + (b.getAmount()/lastDay.getDayOfMonth())*end.getDayOfMonth();
                }
            }
        }

        return (int)budget;
    }
}
