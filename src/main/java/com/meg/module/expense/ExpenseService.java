package com.meg.module.expense;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.meg.module.user.domain.ExpenseOwner;
import com.meg.module.user.domain.ExpenseOwnerRepository;

/**
 * Created by meg on 8/30/17.
 */

public class ExpenseService {

    private static final ExpenseRepository expenseRepository = new ExpenseRepository();

    private final ExpenseOwnerRepository expenseOwnerRepository = new ExpenseOwnerRepository();

    public Expense addExpense(Expense expense){
        expense.setCreatedDateNow();
        expense.updateLastModified();
        return expenseRepository.saveEntity(expense);
    }

    public Expense findExpense(Long passedId) throws NotFoundException, InternalServerErrorException {
        return  expenseRepository.getEntity(passedId);
    }

    public CollectionResponse<Expense> findExpenses(final String cursor, final int limit){
        return  expenseRepository.listEntity(cursor, limit);
    }

    public Expense updateExpense(final Expense expense, final Long id) throws NotFoundException, InternalServerErrorException {
        expense.updateLastModified();
        return expenseRepository.updateEntity(id, expense);
    }

    public void deleteExpense(final Long id) throws NotFoundException {
        expenseRepository.deleteEntity(id);
    }

}
