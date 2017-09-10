package com.meg.module.expense.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.meg.module.core.BaseEntity;
import com.meg.module.expenseowner.domain.ExpenseOwner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * Created by meg on 8/27/17.
 */

@Getter
@Setter(AccessLevel.PACKAGE)
@Entity
@Log
public class Expense extends BaseEntity{

    @NonNull
    public String label;

    @NonNull
    public String description;

    @NonNull
    public int amount;


    /**
     * A ExpenseOwner can have many Expense, but one Expense is only related to one ExpenseOwner. It's the typical ( 1 : N ) relationship
     * Ref<?> does not hold actual references, it only holds the Key<?> and offers a get() method that will do an ofy().load() behind the scenes for you
     * @Load annotations let you configure what gets loaded into the session cache when you load the original entity.
     * With Ref<?> and @Load, you may need fewer calls to the DB; it depends on your usage profile and the shape of your data.
     */
    @Load
    @NonNull
    @Index
    Ref<ExpenseOwner> expenseOwner; //ExpenseOwner is an @Entity

    public Expense() {
        super();
    }

    /**
     * Get actual expenseOwner entity, not just the reference.
     * @return Actual ExpenseOwner entity
     */
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    public ExpenseOwner getExpenseOwner() {
        log.info("getExpenseOwner : " + expenseOwner.get().getId());
        return expenseOwner.get();
    }

    /**
     * Set actual expenseOwner reference, not the entity.
     */
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    public void setExpenseOwner(final ExpenseOwner expenseOwner) {
        log.info("setExpenseOwner : " + expenseOwner.getId());
        this.expenseOwner = Ref.create(expenseOwner);
    }


    @Override
    public String toString() {
        return String.format(
                "label='%s', description='%s']",
                label, description);
    }
}
