package com.meg.module.expenseowner.domain;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.meg.module.expense.domain.Expense;
import com.meg.module.expenseowner.web.ExpenseOwnerService;

/**
 * Created by meg on 9/8/17.
 */
public class ExpenseOwnerServiceImp implements ExpenseOwnerService{

    private final ExpenseOwnerRepository expenseOwnerRepository = new ExpenseOwnerRepository();

    public ExpenseOwner add(final ExpenseOwner expenseOwner) {
        expenseOwner.setCreatedDateNow();
        expenseOwner.updateLastModified();
        return expenseOwnerRepository.saveEntity(expenseOwner);
    }

    /**
     * User searches for a ExpenseOwner with given identifier.
     * @param id the identifier of the Farmer to be searched for.
     * @return the stored ExpenseOwner
     * @throws UnauthorizedException
     */
    public ExpenseOwner find(final Long id) throws InternalServerErrorException, NotFoundException {
        return expenseOwnerRepository.getEntity(id);
    }

    /**
     * Lists all ExpenseOwners
     * @param cursor
     * @param limit maximum number of entities per page
     * @return
     */
    public CollectionResponse<ExpenseOwner> findAll(final String cursor, final Integer limit){
        return expenseOwnerRepository.listEntity(cursor, limit);
    }

    public CollectionResponse<Expense> findAllExpensesByExpenseOwner(final Long id) throws NotFoundException, InternalServerErrorException {
        ExpenseOwner expenseOwner = expenseOwnerRepository.getEntity(id);
        expenseOwner.getExpenses();
        //return list of entities
        return CollectionResponse.<Expense>builder()
                .setItems(expenseOwner.getExpenses())
                .build();
    }



}
