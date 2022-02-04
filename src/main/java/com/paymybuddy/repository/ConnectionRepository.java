package com.paymybuddy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.paymybuddy.model.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Integer> {
	 @Query("SELECT connection FROM Connection connection WHERE connection.userId = ?1")
		Connection getConnectionByUserID(int userID);
}
