package com.meg.module.expense.web;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.meg.module.expense.domain.Expense;
import com.meg.module.expense.domain.ExpenseRequest;

/**
 * Created by meg on 8/30/17.
 */

public interface ExpenseService {

    Expense addExpense(ExpenseRequest expenseRequest) throws NotFoundException, InternalServerErrorException;

    Expense findExpense(Long passedId) throws NotFoundException, InternalServerErrorException;

    CollectionResponse<Expense> findExpenses(final String cursor, final int limit);

    Expense updateExpense(final Expense expense, final Long id) throws NotFoundException, InternalServerErrorException;

    void deleteExpense(final Long id) throws NotFoundException ;

    CollectionResponse<Expense> findExpensesByExpenseOwner(final String cursor, final int limit, Long expenseOwnerId)throws NotFoundException, InternalServerErrorException;

}
