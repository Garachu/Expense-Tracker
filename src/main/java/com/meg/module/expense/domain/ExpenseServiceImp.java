package com.meg.module.expense.domain;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.meg.module.expense.web.ExpenseService;
import com.meg.module.expenseowner.domain.ExpenseOwnerRepository;
import lombok.extern.java.Log;

/**
 * Created by meg on 9/8/17.
 */

@Log
public class ExpenseServiceImp implements ExpenseService {

    private static final ExpenseRepository expenseRepository = new ExpenseRepository();

    private final ExpenseOwnerRepository expenseOwnerRepository = new ExpenseOwnerRepository();

    public Expense addExpense(ExpenseRequest expenseRequest) throws NotFoundException, InternalServerErrorException {

        log.info("addExpense:  " + expenseRequest.getExpenseOwner());

        //Create and set all properties
        Expense expense = new Expense();
        expense.setCreatedDateNow();
        expense.updateLastModified();
        expense.setExpenseOwner(expenseOwnerRepository.getEntity(expenseRequest.getExpenseOwner()));
        expense.setLabel(expenseRequest.getLabel());
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.amount);
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

    public CollectionResponse<Expense> findExpensesByExpenseOwner(final String cursor, final int limit, Long expenseOwnerId) throws NotFoundException, InternalServerErrorException {
        return expenseRepository
                .findExpensesByExpenseOwner(cursor, expenseOwnerRepository.getEntity(expenseOwnerId), limit);
    }

}
