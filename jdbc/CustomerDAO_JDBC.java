package jdbc;

import dao.CustomerDAO;

import java.util.ArrayList;
import java.util.List;

import beans.Customer;
import beans.User;

import java.sql.*;

public class CustomerDAO_JDBC implements CustomerDAO {
    Connection dbConnection;

    public CustomerDAO_JDBC(Connection dbconn){
        dbConnection = dbconn;
    }

    @Override
    public Customer GetCustomerByUser (User user) {
        Customer c = null;
        String sql;
        Statement stmt = null;
        Long mobile_no = user.get_mobile_no();
        try{
            stmt = dbConnection.createStatement();
            sql = "select * from User where mobile_no = " + mobile_no + ";";
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Long mob_no = rs.getLong("mobile_no");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String password = rs.getString("password");
                String line1 = rs.getString ("address_line_1");
                String line2 = rs.getString ("address_line_2");
                String line3 = rs.getString ("address_line_3");

                c = new Customer (mob_no, password, fname, lname, line1, line2, line3);
                // Add exception handling here if more than one row is returned
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return c;
    }
    @Override
    public Customer GetCustomerByMobileNo (Long mobile_no)
    {
        Customer c = new Customer ();
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from customer where mobile_no = " + mobile_no;
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Long mob_no = rs.getLong("mobile_no");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                c.set_mobile_no(mob_no);
                c.set_fname(fname);
                c.set_lname(lname);
                break;
                // Add exception handling here if more than one row is returned
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return c;
    }
    public void AddCustomer (Customer customer)
    {
        PreparedStatement preparedStatement = null;																																																																																																																																													
        String sql;
        sql = "insert into user(mobile_no, password, fname, lname, address_line_1, address_line_2, address_line_3)"+
            " values (?, ?, ?, ?, ?, ?, ?)";

        try {
            dbConnection.setAutoCommit(false);
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong   (1, customer.get_mobile_no());
            preparedStatement.setString (2, customer.get_password());
            preparedStatement.setString (3, customer.get_fname());
            preparedStatement.setString (4, customer.get_lname());
            preparedStatement.setString (5, customer.get_address_line_1());
            preparedStatement.setString (6, customer.get_address_line_2());
            preparedStatement.setString (7, customer.get_address_line_3());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();


            sql = "insert into Customer (mobile_no) values(?)";
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setLong (1, customer.get_mobile_no());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                dbConnection.rollback();

            } catch (SQLException eq) {
                System.out.println(eq.getMessage());
            }
        }

        try{
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}