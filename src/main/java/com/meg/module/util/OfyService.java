package com.meg.module.util;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.meg.module.core.BaseEntity;
import com.meg.module.expense.domain.Expense;
import com.meg.module.expenseowner.domain.ExpenseOwner;
import lombok.experimental.UtilityClass;

/**
 * Created by meg on 8/30/17.
 */

/**
 * A utility class is a class that is just a namespace for functions.
 * No instances of it can exist, and all its members are static.
 * @UtilityClass annotation automatically turns the annotated class into a utility class
 * A utility class cannot be instantiated.
 * By marking your class with @UtilityClass, lombok will automatically generate a private constructor that throws an exception, flags as error any explicit constructors you add, and marks the class final.
 * If the class is an inner class, the class is also marked static.
 */

/**
 * Registering Entities
 * Before you use Objectify to load or save data, you must register all entity classes for your application
 * Objectify will introspct these classes and their annotations to build a metamodel which is used to efficiently manipulate entities at runtime
 * Registration must be done at application startup before Objectify is used.
 * Registration must be single-threaded. Do not register() from multiple threads
 * All entity classes must be registered, including polymorphic subclasses
 * @Embed classes do not need to be registered
 */

@UtilityClass
public class OfyService {

    static {
        ObjectifyService.register(BaseEntity.class);
        ObjectifyService.register(Expense.class);
        ObjectifyService.register(ExpenseOwner.class);
    }


    /**
     * ObjectifyService.ofy()
     * The method to call at any time to get the current Objectify, which may change depending on txn context
     *
     * All datastore operations begin with an instance of Objectify
     */
    public static Objectify ofy() {

        return ObjectifyService.ofy();
    }


    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
