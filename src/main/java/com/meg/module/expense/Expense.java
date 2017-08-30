package com.meg.module.expense;

import com.googlecode.objectify.annotation.Entity;
import com.meg.module.core.BaseEntity;
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

    private Expense(){
        super();
    }

    public Expense(String label, String description, Double amount) {
        this.label = label;
        this.description = description;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return String.format(
                "Expense[id=%s, label='%s', description='%s']",
                getId(), label, description);
    }
}
