package jdbc;

import beans.User;
import beans.Customer;

import dao.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDAO_JDBC implements UserDAO {
    Connection dbConnection;

    public UserDAO_JDBC(Connection dbconn){
        dbConnection = dbconn;
    }

    @Override
    public User GetUser (Long mobile_no, String password)
    {
        User u = null;
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from User where mobile_no = " + mobile_no;// + " and password = " + password +";";
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next ()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String line1 = rs.getString ("address_line_1");
                String line2 = rs.getString ("address_line_2");
                String line3 = rs.getString ("address_line_3");
                u = new User (mobile_no, password, fname, lname, line1, line2, line3);
                break;
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return u;
    }
}