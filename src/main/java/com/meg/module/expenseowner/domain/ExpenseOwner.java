package com.meg.module.expenseowner.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Load;
import com.meg.module.core.BaseEntity;
import com.meg.module.expense.domain.Expense;
import lombok.Getter;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by meg on 8/27/17.
 */


@Entity
@Getter
public class ExpenseOwner extends BaseEntity {

    @NonNull
    public String label;

    @NonNull
    public String fullname;

    @NonNull
    public String description;

    private ExpenseOwner() {
        super();
    }

    /**
     * List of references to all the expenses that the ExpenseOwner owns.
     *
     * Load annotation explanation:
     * Makes for optimal batch-by-type queries to datastore, also caches it intelligently by optimalisation algorithm.
     */
    @Load
    private List<Ref<Expense>> expenses = new ArrayList<>();

    /**
     * getExpenses function iterates through the expense references and places the actual expense entities into a List that is returned
     * @return List with actual Expense entities
     */
    public List<Expense> getExpenses() {
        final List<Expense> expenseList = new ArrayList<>();

        for (final Ref<Expense> expenseRef : expenses) {
            expenseList.add(expenseRef.get());
        }

        return expenseList;
    }

    /**
     * setExpenses function sets a list of references to expenses.
     */
    public void setExpenses(final List<Expense> inputExpenseList) {
        for (final Expense expense : inputExpenseList) {
            addExpense(expense);
        }
    }

    /**
     * Private function to add a single Expense as Ref to the existing Ref List.
     */
    private void addExpense(final Expense expense) {
        expenses.add(Ref.create(expense));
    }


    @Override
    public String toString() {
        return String.format(
                "Expense_Owner[id=%s, label='%s', fullname='%s', description=%s]",
                getId(), label, fullname, description);
    }
}
