package com.expense.module.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Created by meg on 8/27/17.
 */


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expense")
public class Expense {

    @Id
    public String id;

    public String label;

    public String description;

    public Double amount;

    public LocalDate expenseDate;

    public Expense(String label, String description, Double amount, LocalDate expenseDate) {
        this.label = label;
        this.description = description;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }


    @Override
    public String toString() {
        return String.format(
                "Expense[id=%s, label='%s', description='%s']",
                id, label, description);
    }
}
