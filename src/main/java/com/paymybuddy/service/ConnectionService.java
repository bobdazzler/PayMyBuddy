package com.paymybuddy.service;

import java.util.List;

import com.paymybuddy.model.Connection;
import com.paymybuddy.model.User;

public interface ConnectionService {
List<Connection> getAllConnections();
public User checkingIfUserExist(String username);
}
