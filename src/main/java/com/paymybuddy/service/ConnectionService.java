package com.paymybuddy.service;
import java.util.List;
import com.paymybuddy.model.Connection;
import com.paymybuddy.model.User;
public interface ConnectionService {
public User checkingIfUserExist(String userName);
Integer gettingRecieverId(String userName);
 List<Connection> listOfConnectedEmail();
}
