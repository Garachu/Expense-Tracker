package com.expense;

import com.expense.module.domain.Expense;
import com.expense.module.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;

@SpringBootApplication
@EntityScan(basePackages = "com.expense")
@ComponentScan(basePackages = "com.expense")
public class ExpenseTrackerApplication implements CommandLineRunner {

	@Autowired
	private ExpenseRepository expenseRepository;

	public static void main(String[] args) {

		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		expenseRepository.deleteAll();
		String date = "2016-08-16";

		//default, ISO_LOCAL_DATE
		LocalDate localDate = LocalDate.parse(date);

		expenseRepository.save(new Expense("test1", "test1", (double) 200, localDate));

		// fetch all customers
		System.out.println("Expenses found with findAll():");
		System.out.println("-------------------------------");
		for (Expense expense : expenseRepository.findAll()) {
			System.out.println(expense);
		}
		System.out.println();
	}
}
