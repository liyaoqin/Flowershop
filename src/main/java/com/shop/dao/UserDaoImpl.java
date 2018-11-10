package com.shop.dao;

import com.shop.domain.User;
import com.shop.jdbc.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao{
    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
    public void addUser(User user) throws SQLException {
        String sql="insert into user value(?,?,?,?)";
        qr.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getPhone());
    }

    @Override
    public User findUser(User user) throws SQLException {
        String sql="select * from user where username=?";
        User existUser = qr.query(sql, new BeanHandler<User>(User.class), user.getUsername());
        return existUser;
    }

    @Override
    public User loginUser(User user) throws SQLException {
        String sql="select * from user where username=? and password=?";
        User u=qr.query(sql,new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
        return u;
    }
}
