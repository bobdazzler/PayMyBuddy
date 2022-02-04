package com.paymybuddy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.paymybuddy.model.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	 @Query("SELECT account FROM Account account WHERE account.userId = ?1")
	 		Account getAccountByUserID(int userID);
}
