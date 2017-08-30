package com.meg.module.expense.web;

import com.meg.module.expense.Expense;
import com.meg.module.expense.ExpenseService;

/**
 * Created by meg on 8/30/17.
 */


public class ExpenseEndPoint {

    private static final ExpenseService expenseService = new ExpenseService();

    public Expense addExpense(final Expense expense){
        return expenseService.add(expense);
    }

}
