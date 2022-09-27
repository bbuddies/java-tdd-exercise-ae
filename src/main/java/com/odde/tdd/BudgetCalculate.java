package com.odde.tdd;

public class BudgetCalculate {
    private final BudgetRepo repo;

    public BudgetCalculate(BudgetRepo repo) {
        this.repo = repo;
    }

    public long getBudget (Period period) {
        if (null == period.getStart() || null == period.getEnd()) {
            return 0;
        }

        return repo.findAll().stream().mapToLong(budget -> budget.getAmountOfOverlapping(period)).sum();
    }

}
