package com.github.juliherms.expensetrackerapi.repository;

import com.github.juliherms.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findByCategory(String category, Pageable page);

    // SELECT * FROM tbl_expenses WHERE name LIKE '%keyword%'
    Page<Expense> findByNameContaining(String keyword, Pageable page);

    // SELECT * FROM tbl_expenses WHERE date BETWEEN 'startDate' AND 'endDate'
    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);

    // SELECT * FROM tbl_expenses WHERE user_id=?
    Page<Expense> findByUserId(Long userId, Pageable page);

    // SELECT * FROM tbl_expenses WHERE user_id=? AND id=?
    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);

    // SELECT * FROM tbl_expenses WHERE user_id=? AND category=?
    Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable page);

    // SELECT * FROM tbl_expenses WHERE user_id=? AND name LIKE '%keyword%'
    Page<Expense> findByUserIdAndNameContaining(Long userId, String keyword, Pageable page);

    //SELECT * FROM tbl_expenses WHERE user_id=? AND date BETWEEN 'startDate' NAD 'endDate
    Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page);

}
