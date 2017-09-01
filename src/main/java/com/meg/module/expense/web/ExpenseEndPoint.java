package com.meg.module.expense.web;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.DefaultValue;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.meg.module.expense.Expense;
import com.meg.module.expense.ExpenseService;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import static com.google.api.server.spi.config.ApiMethod.HttpMethod.*;

/**
 * Created by meg on 8/30/17.
 */


/*@Api contains the configuration details of the backend API
 *@ApiMethod marks a class method that is part of the backend API.
 *Methods that are not marked with @ApiMethod are not included when you generate client libraries and discovery docs. The @ApiMethod annotation can also be used to override the API configuration for a specific method.
 */
@Api(
        name = "freekenya",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "africa.incentro.com",
                ownerName = "africa.incentro.com",
                packagePath = "cloud"
        ),
        apiKeyRequired = AnnotationBoolean.TRUE
)
public class ExpenseEndPoint {

    private static final ExpenseService expenseService = new ExpenseService();


    /**
     * Create a Expense in the datastoreq33
     */
    @ApiMethod(name = "addExpense", path="farms", httpMethod = POST)
    public Expense addExpense(final Expense expense){
        return expenseService.addExpense(expense);
    }

    /**
     * Obtain multiple Expenses from the datastore
     */
    @ApiMethod(name = "listExpense", path="expenses", httpMethod = GET)
    public CollectionResponse<Expense> listExpense(@Nullable @Named("cursor") final String cursor, @Nullable @Named("limit") @DefaultValue("50") final Integer limit) throws UnauthorizedException {
        return expenseService.findExpenses(cursor, limit);
    }

    /**
     * Obtain one Expense that has the supplied id from the datastore
     */
    @ApiMethod(name = "getExpense", path="expenses/{id}", httpMethod = GET)
    public Expense getExpense(@NotNull @Named("id") final Long id) throws NotFoundException, UnauthorizedException, InternalServerErrorException {
        return expenseService.findExpense(id);
    }

    /**
     * Update the Expense in the datastore that has the supplied id
     */
    @ApiMethod(name = "updateExpense", path="expenses/{id}", httpMethod = PUT)
    public Expense updateFarm(@NotNull @Named("id") final Long id, final Expense expense) throws NotFoundException, UnauthorizedException, InternalServerErrorException {
        return expenseService.updateExpense(expense, id);
    }

    /**
     * Delete a Expense in the datastore that has the supplied id
     */
    @ApiMethod(name = "deleteExpense", path="expenses/{id}", httpMethod = DELETE)
    public void deleteExpense(@NotNull @Named("id") final Long id) throws NotFoundException, UnauthorizedException {
        expenseService.deleteExpense(id );
    }

}
