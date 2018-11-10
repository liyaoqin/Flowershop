package com.shop.service;

import com.shop.domain.User;

import java.sql.SQLException;

public interface UserService {
    public void addUser(User user) throws SQLException;
    public User findUser(User user) throws SQLException;
    public User loginUser(User user) throws SQLException;
}
