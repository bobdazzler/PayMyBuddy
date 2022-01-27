package com.paymybuddy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.paymybuddy.model.User;
@Service
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
