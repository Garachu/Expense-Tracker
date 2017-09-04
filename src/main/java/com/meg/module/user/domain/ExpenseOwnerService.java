package com.meg.module.user.domain;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

/**
 * Created by meg on 9/3/17.
 */
public class ExpenseOwnerService {

    private final ExpenseOwnerRepository expenseOwnerRepository = new ExpenseOwnerRepository();

    public ExpenseOwner add(final ExpenseOwner expenseOwner) {
        expenseOwner.setCreatedDateNow();
        expenseOwner.updateLastModified();
        return expenseOwnerRepository.saveEntity(expenseOwner);
    }

    /**
     * User searches for a Farmer with given identifier.
     * @param id the identifier of the Farmer to be searched for.
     * @return the stored Farmer
     * @throws UnauthorizedException
     */
    public ExpenseOwner find(final Long id) throws InternalServerErrorException, NotFoundException {
        return expenseOwnerRepository.getEntity(id);
    }

    /**
     * Lists all Farmer
     * @param cursor
     * @param limit maximum number of entities per page
     * @return
     * @throws UnauthorizedException
     */
    public CollectionResponse<ExpenseOwner> findAll(final String cursor, final Integer limit) throws UnauthorizedException {
        //isAuthenticated(user);
        return expenseOwnerRepository.listEntity(cursor, limit);
    }
}
