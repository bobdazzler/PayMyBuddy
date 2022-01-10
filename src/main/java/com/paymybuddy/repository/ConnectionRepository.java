package com.paymybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paymybuddy.model.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Integer> {

}
