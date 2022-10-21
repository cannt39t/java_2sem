package com.sport.net.dao.impl;

import com.sport.net.dao.UserDao;
import com.sport.net.model.User;
import com.sport.net.util.PasswordUtil;
import com.sport.net.util.PostgresConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final Connection connection = PostgresConnectionUtil.getConnection();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from users_temp;";
            ResultSet resultSet = statement.executeQuery(sql);

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("key"),
                        resultSet.getString("salt")
                );
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            LOGGER.warn("Failed execute get all query", e);
            return List.of();
        }
    }

    @Override
    public void save(User user) {
        String salt = PasswordUtil.generateSalt(512).get().toString();
        String key = PasswordUtil.hashPassword(user.getPassword(), salt).get().toString();

        String sql = "INSERT into users_temp (login, key, salt) VALUES (?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, key);
            preparedStatement.setString(3, salt);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("Failed execute save query", e);
        }
    }

    @Override
    public boolean login(User user) {
        try {
            String sql = "SELECT * FROM users_temp WHERE login LIKE ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            User user_from_db = null;
            try {
                preparedStatement.setString(1, "%" + user.getLogin() + "%");
                ResultSet resultSet = preparedStatement.executeQuery();


                while (resultSet.next()) {
                    user_from_db = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("key"),
                            resultSet.getString("salt")
                    );
                }
            } catch (SQLException e) {
                LOGGER.warn("Failed execute save query", e);
            }

            String salt = user_from_db.getSalt();
            String password = user.getPassword();
            String key = user_from_db.getKey();
            return PasswordUtil.verifyPassword(password, key, salt);

        } catch (SQLException e) {
            LOGGER.warn("Failed execute get all query", e);
            return false;
        }
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User("asfdsa", "asdfasfdsa");
        userDao.save(user);
        System.out.println(userDao.login(user));
    }


}
