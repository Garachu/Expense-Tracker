package com.meg.module.user.web;

import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.meg.module.user.domain.ExpenseOwner;
import com.meg.module.user.domain.ExpenseOwnerService;

import javax.validation.constraints.NotNull;

import static com.google.appengine.repackaged.com.google.api.client.http.HttpMethods.GET;
import static com.google.appengine.repackaged.com.google.api.client.http.HttpMethods.POST;

/**
 * Created by meg on 9/3/17.
 */


@Api(
        name = "expenseOwner",
        version = "v1",
        namespace =
        @ApiNamespace(
                ownerDomain = "expense.tracker.com",
                ownerName = "expense.tracker.com",
                packagePath = ""
        ),
        // [START_EXCLUDE]
        issuers = {
                @ApiIssuer(
                        name = "firebase",
                        issuer = "https://securetoken.google.com/test-endpointframework",
                        jwksUri = "https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com")
        }
        // [END_EXCLUDE]
)
public class ExpenseOwnerEndPoint {

    private final ExpenseOwnerService eos = new ExpenseOwnerService();

    /**
     * Create a Expense in the datastoreq33
     */
    @ApiMethod(name = "addExpenseOwner", path="expenseOwners", httpMethod = POST)
    public ExpenseOwner addExpenseOwner(final ExpenseOwner eo){
        return eos.add(eo);
    }

    /**
     * Obtain multiple Expenses from the datastore
     */
    @ApiMethod(name = "listExpenseOwners", path="expenseOwners", httpMethod = GET)
    public CollectionResponse<ExpenseOwner> listExpenseOwners(@Nullable @Named("cursor") final String cursor, @Nullable @Named("limit") @DefaultValue("50") final Integer limit) throws UnauthorizedException, UnauthorizedException {
        return eos.findAll(cursor, limit);
    }

    /**
     * Obtain one Expense that has the supplied id from the datastore
     */
    @ApiMethod(name = "getExpenseOwner", path="expenseOwners/{id}", httpMethod = GET)
    public ExpenseOwner getExpenseOwner(@NotNull @Named("id") final Long id) throws NotFoundException, UnauthorizedException, InternalServerErrorException {
        return eos.find(id);
    }
}
