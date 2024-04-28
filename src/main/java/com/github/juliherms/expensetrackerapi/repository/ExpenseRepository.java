package com.github.juliherms.expensetrackerapi.repository;

import com.github.juliherms.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findByCategory(String category, Pageable page);

    // SELECT * FROM tbl_expenses WHERE name LIKE '%keyword%'
    Page<Expense> findByNameContaining(String keyword, Pageable page);
}
