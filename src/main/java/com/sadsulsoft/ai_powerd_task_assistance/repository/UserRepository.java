package com.sadsulsoft.ai_powerd_task_assistance.repository;

import com.sadsulsoft.ai_powerd_task_assistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Custom method to find a user by their username.
    // Spring Data JPA automatically implements this based on the method name.
    Optional<User> findByUsername(String username);
}