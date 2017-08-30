package com.meg.module.expense;

/**
 * Created by meg on 8/30/17.
 */
public class ExpenseService {

    private static final ExpenseRepository expenseRepository = new ExpenseRepository();

    public Expense add (final Expense expense){
        expense.setCreatedDateNow();
        expense.updateLastModified();
        return expenseRepository.insertEntity(expense);
    }




}
