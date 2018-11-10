package com.shop.service;

import com.shop.dao.UserDao;
import com.shop.dao.UserDaoImpl;
import com.shop.domain.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    @Override
    public User findUser(User user) throws SQLException {
        return userDao.findUser(user);
    }

    @Override
    public User loginUser(User user) throws SQLException {
        return userDao.loginUser(user);
    }

}
