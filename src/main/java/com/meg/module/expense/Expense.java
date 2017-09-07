package com.meg.module.expense;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Load;
import com.meg.module.core.BaseEntity;
import com.meg.module.user.domain.ExpenseOwner;
import lombok.Getter;
import lombok.NonNull;

/**
 * Created by meg on 8/27/17.
 */

@Getter
@Entity
public class Expense extends BaseEntity{

    @NonNull
    public String label;

    @NonNull
    public String description;

    @NonNull
    public Double amount;


    /*
     *A ExpenseOwner can have many Expense, but one Expense is only related to one ExpenseOwner. It's the typical ( 1 : N ) relationship
     * ExpenseOwner is an @Entity
     * Ref<?> does not hold actual references, it only holds the Key<?> and offers a get() method that will do an ofy().load() behind the scenes for you
     * @Load annotations let you configure what gets loaded into the session cache when you load the original entity.
     * With Ref<?> and @Load, you may need fewer calls to the DB; it depends on your usage profile and the shape of your data.
     */
    @Load
    @NonNull
    Ref<ExpenseOwner> expenseOwner;


    /**
     * Get actual expenseOwner entity, not just the reference.
     * @return Actual ExpenseOwner entity
     */
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    public ExpenseOwner getExpenseOwner() {
        return expenseOwner.get();
    }

    /**
     * Set actual expenseOwner reference, not the entity.
     */
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    public void setExpenseOwner(final ExpenseOwner expenseOwner) {
        this.expenseOwner = Ref.create(expenseOwner);
    }



    @Override
    public String toString() {
        return String.format(
                "Expense[id=%s, label='%s', description='%s', amount='%d']",
                getId(), label, description, amount);
    }
}
