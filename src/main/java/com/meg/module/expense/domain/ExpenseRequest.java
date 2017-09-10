package com.meg.module.expense.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by meg on 9/8/17.
 */

@Getter
@NoArgsConstructor
public class ExpenseRequest {

    @NonNull
    public String label;

    @NonNull
    public String description;

    @NonNull
    public int amount;

    @NonNull
    public Long expenseOwner;

}
