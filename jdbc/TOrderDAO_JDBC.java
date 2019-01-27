package jdbc;

import dao.TOrderDAO;
import beans.TOrder;

import java.sql.*;
import java.util.ArrayList;


public class TOrderDAO_JDBC implements TOrderDAO {

    Connection dbConnection;

    public TOrderDAO_JDBC(Connection dbconn){
        dbConnection = dbconn;
    }

    @Override
    public ArrayList<TOrder> GetOrderByUserId(Long customer_mobile_no) {
        ArrayList<TOrder> order_list = new ArrayList<TOrder>();
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from TOrder where customer_mobile_no = " + customer_mobile_no;
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long  order_id  = rs.getLong("order_id");
                String delivery_address = rs.getString("delivery_address");
                float total_amount = rs.getFloat("total_amount");

                TOrder order = new TOrder(order_id, customer_mobile_no, total_amount, delivery_address);
                order_list.add(order);
                
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return order_list;
    }

    @Override
    public TOrder GetOrderByOrderId(Long order_id){
        TOrder order = null;
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from TOrder where order_id = " + order_id;
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long customer_mobile_no = rs.getLong("customer_mobile_no");
                String delivery_address = rs.getString("delivery_address");
                float total_amount = rs.getFloat("total_amount");

                order = new TOrder(order_id, customer_mobile_no, total_amount, delivery_address);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return order;
    }

    @Override
    public Long GetNewId() {
        Long len=0L;
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select max(order_id) as len from TOrder"; 
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                len = rs.getLong("len");
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return len+1;    
    }

    @Override
    public void AddTOrder(TOrder torder){
        PreparedStatement preparedStatement = null;
        String sql;
        sql = "insert into TOrder (customer_mobile_no, total_amount, delivery_address)"+
            " values (?, ?, ?)";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, torder.get_customer_mobile_no());
            preparedStatement.setFloat(2, torder.get_total_amount());
            preparedStatement.setString(3, torder.get_delivery_address());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try{
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void DeleteTOrder(TOrder torder){
        PreparedStatement preparedStatement = null;							
        String sql;
        sql = "delete from TOrder where order_id = ?";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, torder.get_order_id());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try{
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void UpdateTorder (TOrder upd){
        PreparedStatement preparedStatement = null;		
        String sql;
        sql = "update TOrder set customer_mobile_no = ? , total_amount = ?, delivery_address = ? where order_id = ?";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, upd.get_customer_mobile_no());
            preparedStatement.setFloat(2, upd.get_total_amount());
            preparedStatement.setString(3, upd.get_delivery_address());
            preparedStatement.setLong(4, upd.get_order_id());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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