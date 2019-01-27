package dao;

import beans.Customer;
import beans.User;

public interface CustomerDAO {
    public Customer GetCustomerByUser (User user);
    public Customer GetCustomerByMobileNo (Long mobile_no);
    public void AddCustomer (Customer customer);
}