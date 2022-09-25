package com.odde.tdd;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetCalculateTest {

    // 验证起始日期为null
    @Test
    public void test_start_is_null () {
        LocalDate start = null;
        LocalDate end = LocalDate.parse("2022-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BudgetCalculate budgetCalculate = new BudgetCalculate();
        Integer budget = budgetCalculate.getBudget(start, end);
        assertEquals(0, budget);
    }

    // 验证结束日期为null
    @Test
    public void test_end_is_null () {
        LocalDate start = LocalDate.parse("2022-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = null;
        BudgetCalculate budgetCalculate = new BudgetCalculate();
        Integer budget = budgetCalculate.getBudget(start, end);
        assertEquals(0, budget);
    }

    // 验证起始日期大于结束日期
    @Test
    public void test_start_after_end () {
        LocalDate start = LocalDate.parse("2022-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BudgetCalculate budgetCalculate = new BudgetCalculate();
        Integer budget = budgetCalculate.getBudget(start, end);
        assertEquals(0, budget);
    }

    // 验证开始/结束日期超出范围
    @Test
    public void test_out_range () {
        LocalDate start = LocalDate.parse("2022-08-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2023-02-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BudgetCalculate budgetCalculate = new BudgetCalculate();
        Integer budget = budgetCalculate.getBudget(start, end);
        assertEquals(6501, budget);
    }

    // 验证相同日
    @Test
    public void test_same_day () {
        LocalDate start = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BudgetCalculate budgetCalculate = new BudgetCalculate();
        Integer budget = budgetCalculate.getBudget(start, end);
        assertEquals(100, budget);
    }

    // 验证相同月（不同日）
    @Test
    public void test_same_month () {
        LocalDate start = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-09-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BudgetCalculate budgetCalculate = new BudgetCalculate();
        Integer budget = budgetCalculate.getBudget(start, end);
        assertEquals(200, budget);
    }

    // 验证跨月
    @Test
    public void test_next_month () {
        LocalDate start = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BudgetCalculate budgetCalculate = new BudgetCalculate();
        Integer budget = budgetCalculate.getBudget(start, end);
        assertEquals(940, budget);
    }

    // 验证间隔月
    @Test
    public void test_other_month () {
        LocalDate start = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-11-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BudgetCalculate budgetCalculate = new BudgetCalculate();
        Integer budget = budgetCalculate.getBudget(start, end);
        assertEquals(1058, budget);
    }

    // 验证跨年
    @Test
    public void test_next_year () {
        LocalDate start = LocalDate.parse("2022-12-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2023-01-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        BudgetCalculate budgetCalculate = new BudgetCalculate();
        Integer budget = budgetCalculate.getBudget(start, end);
        assertEquals(2408, budget);
    }

    //
}
