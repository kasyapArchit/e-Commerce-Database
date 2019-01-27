package jdbc;

import dao.ProductDAO;
import beans.Product;

import java.sql.*;
import java.util.ArrayList;

import dao.ProductDAO;

public class ProductDAO_JDBC implements ProductDAO {

    Connection dbConnection;

    public ProductDAO_JDBC(Connection dbconn){
        dbConnection = dbconn;
    }

    @Override
    public Product GetProductByProductIdAndSeller (Long product_id, Long seller_mobile_no) {
        Product product = null;
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from Product where seller_mobile_no = " + seller_mobile_no +
            " and product_id = " + product_id;
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                String product_name = rs.getString("product_name");
                float price = rs.getFloat("price");
                float offer = rs.getFloat ("offer");
                int available_quantity = rs.getInt ("available_quantity");

                product = new Product (offer, available_quantity, product_id, seller_mobile_no, product_name, price);
                
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return product;
    }

    @Override
    public ArrayList <Product> GetProductBySeller (Long seller_mobile_no) {
        ArrayList <Product> product_list = new ArrayList<Product>();
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from Product where seller_mobile_no = " + seller_mobile_no;
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long product_id  = rs.getLong("product_id");
                String product_name = rs.getString("product_name");
                float price = rs.getFloat("price");
                float offer = rs.getFloat ("offer");
                int available_quantity = rs.getInt ("available_quantity");

                Product product = new Product (offer, available_quantity, product_id, seller_mobile_no, product_name, price);
                product_list.add(product);
                
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return product_list;
    }

    @Override
    public Product GetProductByProductId (Long product_id) {
        Product product = null;
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from Product where product_id = " + product_id;
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long seller_mobile_no = rs.getLong("seller_mobile_no");
                String product_name = rs.getString("product_name");
                float price = rs.getFloat("price");
                float offer = rs.getFloat ("offer");
                int available_quantity = rs.getInt ("available_quantity");

                product = new Product (offer, available_quantity, product_id, seller_mobile_no, product_name, price);
                // product.set_product_id(tproduct_id);
                // product.set_seller_mobile_no(seller_mobile_no);
                // product.set_product_name (product_name);
                // product.set_offer (offer);
                
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return product;
    }
    
    @Override
    public void AddProduct (Product product) {
        PreparedStatement preparedStatement = null;
        String sql;
        sql = "insert into Product (seller_mobile_no, product_name, price, offer, available_quantity)"+
            " values (?, ?, ?, ?, ?)";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, product.get_seller_mobile_no());
            preparedStatement.setString(2, product.get_product_name());
            preparedStatement.setFloat(3, product.get_price());
            preparedStatement.setFloat(4, product.get_offer());
            preparedStatement.setInt(5, product.get_available_quantity());

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
    public void DeleteProduct (Product product) {
        PreparedStatement preparedStatement = null;							
        String sql;
        sql = "delete from Product where product_id = ?";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setLong(1, product.get_product_id());

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
    public void UpdateProduct (Product upd) {
        PreparedStatement preparedStatement = null;		
        String sql;
        sql = "update Product set available_quantity = ? , offer = ?, product_name = ?, seller_mobile_no = ?, price = ? where product_id = ?";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setInt(1, upd.get_available_quantity());
            preparedStatement.setFloat(2, upd.get_offer());
            preparedStatement.setString(3, upd.get_product_name());
            preparedStatement.setLong(4, upd.get_seller_mobile_no());
            preparedStatement.setFloat(5, upd.get_price());
            preparedStatement.setLong(6, upd.get_product_id());

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
    public ArrayList<Product> GetAll () {
        String sql;
        Statement stmt = null;
        ArrayList <Product> product_list = new ArrayList<Product>();
        try{
            stmt = dbConnection.createStatement();
            sql = "select * from Product";
            ResultSet rs = stmt.executeQuery(sql);
                                                                
            //STEP 5: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long product_id  = rs.getLong("product_id");
                Long seller_mobile_no = rs.getLong("seller_mobile_no");
                String product_name = rs.getString("product_name");
                float offer = rs.getFloat ("offer");
                float price = rs.getFloat("price");
                int available_quantity = rs.getInt ("available_quantity");

                product_list.add(new Product (offer, available_quantity, product_id, seller_mobile_no, product_name, price));
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return product_list;
    }
}