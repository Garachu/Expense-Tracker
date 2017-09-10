package com.meg.module.expenseowner.web;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.meg.module.expenseowner.domain.ExpenseOwner;
import com.meg.module.expenseowner.domain.ExpenseOwnerRepository;

/**
 * Created by meg on 9/3/17.
 */
public interface ExpenseOwnerService {

   ExpenseOwner add(final ExpenseOwner expenseOwner);

    /**
     * User searches for a ExpenseOwner with given identifier.
     * @param id the identifier of the ExpenseOwner to be searched for.
     * @return the stored ExpenseOwner
     */
    ExpenseOwner find(final Long id) throws InternalServerErrorException, NotFoundException;

    /**
     * Lists all ExpenseOwners
     * @param cursor
     * @param limit maximum number of entities per page
     * @return
     */
    CollectionResponse<ExpenseOwner> findAll(final String cursor, final Integer limit);

}
