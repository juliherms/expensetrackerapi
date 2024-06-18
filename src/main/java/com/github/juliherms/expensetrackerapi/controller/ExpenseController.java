package com.github.juliherms.expensetrackerapi.controller;

import com.github.juliherms.expensetrackerapi.entity.Expense;
import com.github.juliherms.expensetrackerapi.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page) {
        return expenseService.getAllExpenses(page).toList();
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam Long id) {
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
        return expenseService.updateExpenseDetails(id,expense);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getAllExpensesByCategory(@RequestParam String category, Pageable page) {
        return expenseService.readByCategory(category,page);
    }

    @GetMapping("/expenses/name")
    public List<Expense> getAllExpensesByName(@RequestParam String name, Pageable page) {
        return expenseService.readByName(name,page);
    }


    //api/v1/expenses/date?startDate=2021-01-01&endDate=2021-03-31
    @GetMapping("/expenses/date")
    public List<Expense> getAllExpensesByDate(
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            Pageable page) {
        return expenseService.readByDate(startDate,endDate,page);
    }
}
