package com.github.juliherms.expensetrackerapi.repository;

import com.github.juliherms.expensetrackerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
