package dao;

import beans.User;

public interface UserDAO {
    public User GetUser (Long mobile_no, String password);
}