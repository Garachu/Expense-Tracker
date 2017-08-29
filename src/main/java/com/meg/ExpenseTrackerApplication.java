package com.meg;

import com.meg.module.expense.domain.Expense;
import com.meg.module.expense.repository.ExpenseRepository;
import com.meg.module.user.domain.ApplicationUser;
import com.meg.module.user.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;

@SpringBootApplication
@EntityScan(basePackages = "com.meg")
@ComponentScan(basePackages = "com.meg")
public class ExpenseTrackerApplication implements CommandLineRunner {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

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
		applicationUserRepository.save(new ApplicationUser("meg", "Margaret Wambui", localDate));

		// fetch all customers
		System.out.println("Expenses found with findAll():");
		System.out.println("-------------------------------");
		for (Expense expense : expenseRepository.findAll()) {
			System.out.println(expense);
		}
		System.out.println();
	}
}
