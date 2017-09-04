package com.meg.module.user.domain;

import com.meg.module.core.AbstractRepository;

/**
 * Created by meg on 9/3/17.
 */
public class ExpenseOwnerRepository extends AbstractRepository<Long, ExpenseOwner>{
    public ExpenseOwnerRepository() {
        super(Long.class, ExpenseOwner.class);
    }
}
