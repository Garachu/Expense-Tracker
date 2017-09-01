package com.meg.module.expense;

import com.meg.module.core.AbstractRepository;
import lombok.extern.slf4j.Slf4j;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by meg on 8/30/17.
 */

@Slf4j
class ExpenseRepository extends AbstractRepository<Long, Expense> {

    public ExpenseRepository(){
        super(Long.class, Expense.class);
    }

    public Expense save(Expense obj){
        Expense expense = new Expense("Wednesday Super", "Bananas-40, Tomatoes-80, Dania-5", 125.0);
        ofy().save().entity(expense).now();    // async without the now()
        log.info("Created " + Expense.class.getName() + ".");
        return obj;
    }

    public void delete(){

    }

    public void load(){

    }

}

