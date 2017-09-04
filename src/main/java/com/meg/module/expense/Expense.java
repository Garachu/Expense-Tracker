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

    /**
      * A relationship is simply a key stored as a field in an entity.
      * 1.  com.google.appengine.api.datastore.Key, usually referred to as Key
      * 2.  com.googlecode.objectify.Key, usually referred to as Key<?>
      * 3.  com.googlecode.objectify.Ref
      * Even Key<?>s are not very convenient when you are working with graphs of entities.
      * Objectify provides Ref<?>, which works just like a Key<?> but allows you to directly access the actual entity object as well:
      * Ref<?> fields are stored as native Key fields in the datastore. You can freely swap between Key, Key<?>, and Ref<?> in your Java data model without modifying stored data.
      * The reference to the Expense owner (Expense Entity)
      *
      * Load annotation explanation:
      * Makes for optimal batch-by-type queries to datastore, also caches it intelligently by optimalisation algorithm.
      *
     */
    @Load
    @NonNull
    Ref<ExpenseOwner> expenseOwner; // ExpenseOwner is an @Entity

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
