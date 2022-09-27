package com.odde.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetCalculateTest {
    BudgetRepo repo = mock(BudgetRepo.class);
    BudgetCalculate budgetCalculate = new BudgetCalculate(repo);

    @BeforeEach
    void setUp() {
        ArrayList<Budget> budgetList = new ArrayList<>();
        budgetList.add(new Budget(YearMonth.of(2022, 9), 3000));
        budgetList.add(new Budget(YearMonth.of(2022, 10), 310));
        budgetList.add(new Budget(YearMonth.of(2022, 11), 60));
        budgetList.add(new Budget(YearMonth.of(2022, 12), 31));
        budgetList.add(new Budget(YearMonth.of(2023, 1), 3100));

        givenBudgets(budgetList);
    }

    // 验证起始日期为null
    @Test
    public void test_start_is_null () {
        LocalDate start = null;
        LocalDate end = LocalDate.parse("2022-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(0, budget);
    }

    // 验证结束日期为null
    @Test
    public void test_end_is_null () {
        LocalDate start = LocalDate.parse("2022-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = null;
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(0, budget);
    }

    // 验证起始日期大于结束日期
    @Test
    public void test_start_after_end () {
        LocalDate start = LocalDate.parse("2022-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(0, budget);
    }

    // 验证开始/结束日期超出范围
    @Test
    public void multiple_budgets () {
        LocalDate start = LocalDate.parse("2022-08-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2023-02-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(6501, budget);
    }

    // 验证相同日
    @Test
    public void test_same_day () {
        givenBudgets(Arrays.asList(new Budget(YearMonth.of(2022, 9), 3000)));
        assertQueryAmount("2022-09-24", "2022-09-24", 100);
    }

    private void assertQueryAmount(String startDate, String endDate, int expected) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertEquals(expected, budgetCalculate.getBudget(new Period(start, end)));
    }

    private void givenBudgets(List<Budget> value) {
        when(repo.findAll()).thenReturn(value);
    }

    // 验证相同月（不同日）
    @Test
    public void test_2_days_in_same_month () {
        LocalDate start = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-09-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(200, budget);
    }

    @Test
    void query_start_before_budget() {
        LocalDate start = LocalDate.parse("2022-08-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-09-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(2500, budget);
    }

    @Test
    void query_end_after_budget() {
        LocalDate start = LocalDate.parse("2023-01-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2023-02-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(800, budget);
    }

    @Test
    void query_out_of_budget() {
        LocalDate start = LocalDate.parse("2023-03-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2023-04-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(0, budget);
    }

    // 验证跨月
    @Test
    public void test_across_2_months () {
        LocalDate start = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-10-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(940, budget);
    }

    // 验证间隔月
    @Test
    public void test_across_3_months () {
        LocalDate start = LocalDate.parse("2022-09-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2022-11-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(1058, budget);
    }

    // 验证跨年
    @Test
    public void test_next_year () {
        LocalDate start = LocalDate.parse("2022-12-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2023-01-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long budget = budgetCalculate.getBudget(new Period(start, end));
        assertEquals(2408, budget);
    }

    //
}
