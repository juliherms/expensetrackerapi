package com.github.juliherms.expensetrackerapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Expense name must not be null")
    @Size(min = 3, message = "Expense name must be atleast 3 characteres")
    @Column(name = "expense_name")
    private String name;

    private String description;

    @NotNull(message = "Expense amount should not be null")
    @Column(name = "expense_amount")
    private BigDecimal amount;

    @NotBlank(message = "Category should not be null")
    private String category;

    @NotNull(message = "Date must not be null")
    private Date date;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updated_at;

}
