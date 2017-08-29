package com.meg.module.expense.repository;


import com.meg.module.expense.domain.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by meg on 8/27/17.
 */


public interface ExpenseRepository extends MongoRepository<Expense, String> {
    public Expense findExpenseByLabel(String label);
}
