package com.expense.module.repository;

import com.expense.module.domain.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by meg on 8/27/17.
 */


public interface ExpenseRepository extends MongoRepository<Expense, Integer>, ExpenseRepositoryCustom{
    public Expense findExpenseByLabel(String label);
}
