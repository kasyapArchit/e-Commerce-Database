package jdbc;

import dao.OrderItemDAO;
import beans.OrderItem;

import java.sql.*;
import java.util.ArrayList;

public class OrderItemDAO_JDBC implements OrderItemDAO{

    Connection dbConnection;

    public OrderItemDAO_JDBC(Connection dbconn){
        dbConnection = dbconn;
    }

    @Override
    public OrderItem GetProductByOrderItemId (Long order_item_id){
        OrderItem order_item = null;
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from OrderItem where order_item_id = " + order_item_id;
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long order_id = rs.getLong("order_id");
                Long product_id = rs.getLong("product_id");
                float amount = rs.getFloat("amount");
                int quantity = rs.getInt ("quantity");
                int stat = rs.getInt ("stat");

                order_item = new OrderItem(order_item_id, order_id, product_id, amount, quantity, stat);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return order_item;
    }

    @Override
    public ArrayList<OrderItem> GetProductByOrderId (Long order_id){
        ArrayList<OrderItem> order_item_list = new ArrayList<OrderItem>();
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from OrderItem where order_id = " + order_id;
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long order_item_id = rs.getLong("order_item_id");
                Long product_id = rs.getLong("product_id");
                float amount = rs.getFloat("amount");
                int quantity = rs.getInt ("quantity");
                int stat = rs.getInt ("stat");

                OrderItem order_item = new OrderItem(order_item_id, order_id, product_id, amount, quantity, stat);
                order_item_list.add(order_item);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return order_item_list;
    }

    @Override
    public Long GetNewId() {
        Long sz=0L;
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select max(order_item_id) as len from OrderItem"; 
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                sz = rs.getLong("len");
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return sz+1;    
    }

    @Override
    public void AddOrderItem (OrderItem order_item){
        PreparedStatement preparedStatement = null;
        String sql;
        sql = "insert into OrderItem(order_id, product_id, amount, quantity, stat)"+" values (?, ?, ?, ?, ?)";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, order_item.get_order_id());
            preparedStatement.setLong(2, order_item.get_product_id());
            preparedStatement.setFloat(3, order_item.get_amount());
            preparedStatement.setInt(4, order_item.get_quantity());
            preparedStatement.setInt(5, order_item.get_stat());

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
    public void DeleteOrderItem (OrderItem order_item){
        PreparedStatement preparedStatement = null;							
        String sql;
        sql = "delete from OrderItem where order_item_id = ?";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, order_item.get_order_item_id());

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
    public void UpdateOrderItem (OrderItem upd){
        PreparedStatement preparedStatement = null;		
        String sql;
        sql = "update OrderItem set order_id = ? , product_id = ?, amount = ?, quantity = ?, stat = ? where order_item_id = ?";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, upd.get_order_id());
            preparedStatement.setLong(2, upd.get_product_id());
            preparedStatement.setFloat(3, upd.get_amount());
            preparedStatement.setInt(4, upd.get_quantity());
            preparedStatement.setInt(5, upd.get_stat());
            preparedStatement.setLong(6, upd.get_order_item_id());

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