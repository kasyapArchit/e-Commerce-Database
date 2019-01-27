package jdbc;

import dao.TReturnsDAO;
import beans.TReturns;

import java.sql.*;
import java.util.ArrayList;

public class TReturnsDAO_JDBC implements TReturnsDAO{

    Connection dbConnection;

    public TReturnsDAO_JDBC(Connection dbconn){
        dbConnection = dbconn;
    }

    @Override
    public TReturns GetTReturnsByOrderItemId (Long order_item_id){
        TReturns tReturns = null;
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from TReturns where order_item_id = " + order_item_id;
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                float amount = rs.getFloat("amount");
                int stat = rs.getInt ("stat");

                tReturns = new TReturns (order_item_id, amount, stat);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return tReturns;
    }

    @Override
    public ArrayList<TReturns> GetAllTReturns (){
        String sql;
        Statement stmt = null;
        ArrayList <TReturns> tReturns_list = new ArrayList<TReturns>();
        try{
            stmt = dbConnection.createStatement();
            sql = "select * from TReturns";
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long order_item_id = rs.getLong("order_item_id");
                float amount = rs.getFloat("amount");
                int stat = rs.getInt ("stat");

                TReturns tReturns = new TReturns (order_item_id, amount, stat);
                tReturns_list.add(tReturns);
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return tReturns_list;
    }

    @Override
    public void AddTReturns (TReturns tReturns){
        PreparedStatement preparedStatement = null;
        String sql;
        sql = "insert into TReturns(order_item_id, amount, stat)"+" values (?, ?, ?)";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, tReturns.get_order_item_id());
            preparedStatement.setFloat(2, tReturns.get_amount());
            preparedStatement.setInt(3, tReturns.get_stat());

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
    public void DeleteTReturns (TReturns tReturns){
        PreparedStatement preparedStatement = null;							
        String sql;
        sql = "delete from TReturns where order_item_id = ?";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, tReturns.get_order_item_id());

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
    public void UpdateTReturns (TReturns upd){
        PreparedStatement preparedStatement = null;		
        String sql;
        sql = "update TReturns set amount = ? , stat = ? where order_item_id = ?";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setFloat(1, upd.get_amount());
            preparedStatement.setInt(2, upd.get_stat());
            preparedStatement.setLong(3, upd.get_order_item_id());

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