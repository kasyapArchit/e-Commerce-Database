package jdbc;

import beans.*;
import dao.*;
import java.sql.*;

public class SellerDAO_JDBC implements SellerDAO {
    Connection dbConnection;

    public SellerDAO_JDBC(Connection dbconn) {
        dbConnection = dbconn;
    }

    @Override
    public Seller GetSellerByUser (User user) {
        Seller s = null;
        String sql;
        Statement stmt = null;
        Long mobile_no = user.get_mobile_no();
        try{
            stmt = dbConnection.createStatement();
            sql = "select * from Seller where mobile_no = " + mobile_no + ";";
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Long gst_no = rs.getLong("gst_no");
                Boolean appr = rs.getBoolean("approved");
                s = (new Seller (mobile_no, user.get_password(), 
                user.get_fname(),user.get_lname(), user.get_address_line_1(),
                user.get_address_line_2(),
                user.get_address_line_3(), appr, gst_no));
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return s;
    }

    @Override
    public void AddSeller (Seller seller) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
        String sql;
        sql = "insert into user(mobile_no, password, fname, lname, address_line_1, address_line_2, address_line_3)"+
            " values (?, ?, ?, ?, ?, ?, ?)";

        try {
            dbConnection.setAutoCommit(false);
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong   (1, seller.get_mobile_no());
            preparedStatement.setString (2, seller.get_password());
            preparedStatement.setString (3, seller.get_fname());
            preparedStatement.setString (4, seller.get_lname());
            preparedStatement.setString (5, seller.get_address_line_1());
            preparedStatement.setString (6, seller.get_address_line_2());
            preparedStatement.setString (7, seller.get_address_line_3());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();


            sql = "insert into Seller (mobile_no, gst_no) values(?)";
            preparedStatement = dbConnection.prepareStatement (sql);
            preparedStatement.setLong (1, seller.get_mobile_no ());
            preparedStatement.setLong (2, seller.get_gst_no ());
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