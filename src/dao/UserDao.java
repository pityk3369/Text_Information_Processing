package dao;

import domain.User;

public interface UserDao {

    User findUserByUsernameAndPassword(String username, String password);
}
