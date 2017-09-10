package com.meg.module.expense.domain;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.cmd.Query;
import com.meg.module.core.AbstractRepository;
import com.meg.module.expenseowner.domain.ExpenseOwner;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by meg on 8/30/17.
 */

@Slf4j
class ExpenseRepository extends AbstractRepository<Long, Expense> {

    public ExpenseRepository(){
        super(Long.class, Expense.class);
    }

    public CollectionResponse<Expense> findExpensesByExpenseOwner(final String cursor, ExpenseOwner expenseOwner, final int limit){

        log.info("Fetching Expenses By ExpenseOwner");

        //fetch expenses by Ref<ExpenseOwner>
        Query<Expense> query = ofy().load().type(Expense.class).filter("expenseOwner", Ref.create(expenseOwner)).limit(limit);

        //check cursor
        if (cursor != null) {

            //cursor exists, so start at that point instead of at the beginning
            //public static Cursor fromWebSafeString(java.lang.String encodedCursor)
            query = query.startAt(Cursor.fromWebSafeString(cursor));
            log.info("Cursor:" + Cursor.fromWebSafeString(cursor));

        } else {
            //cursor does not exist, so start at the beginning
            log.info("No Cursor");
        }

        //make iterator and obj list
        final QueryResultIterator<Expense> queryIterator = query.iterator();

        /**
         * The application can get a web-safe string representing the cursor by calling the Cursor object's toWebSafeString() method,
         * and can later use the static method Cursor.fromWebSafeString() to reconstitute the cursor from the string.
        */
        String cursorString = queryIterator.getCursor().toWebSafeString();

        final List<Expense> objList = new ArrayList<>();

        //iterate through results and add them to list
        while (queryIterator.hasNext()) {
            objList.add(queryIterator.next());
        }

        //return list of entities
        return CollectionResponse.<Expense>builder()
                .setItems(objList)
                .setNextPageToken(cursorString)
                .build();

    }

}

