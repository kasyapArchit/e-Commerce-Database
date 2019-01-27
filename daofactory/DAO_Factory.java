package daofactory;

import dao.*;
import jdbc.*;

import java.lang.*;
import java.sql.*;
/*
	Methods to be called in the following order:

	1. activateConnection
	2. 	Any number getDAO calls with any number of database transactions
	3. deactivateConnection
*/
public class DAO_Factory{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	// Modify the DB_URL string, userid and password depending upon the database you want to connect to 
	// In the following string, you are connecting a adatabase named "daoproject"
	static final String DB_URL = "jdbc:mysql://localhost/ecomm";
	static final String USER = "root";
	static final String PASS = "qwer7894";

	Connection dbconnection = null;

	// You can add additional DAOs here as needed. Generally one DAO per class
    // StudentDAO studentDAO = null;

    CustomerDAO customer_dao = null;
    SellerDAO seller_dao = null;
    ProductDAO product_dao = null;
	UserDAO user_dao = null;
	ComplaintDAO complaint_dao = null;
	TOrderDAO tOrder_dao = null;
	OrderItemDAO order_item_dao = null;
	TReturnsDAO tReturns_dao = null;

	boolean activeConnection = false;

	public DAO_Factory()
	{
		dbconnection = null;
		activeConnection = false;
	}

	public void activateConnection() throws Exception
	{
		if( activeConnection == true )
			throw new Exception("Connection already active");

		System.out.println("Connecting to database...");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			dbconnection = DriverManager.getConnection(DB_URL,USER,PASS);
			activeConnection = true;
		} catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	// public StudentDAO getStudentDAO() throws Exception
	// {
	// 	if( activeConnection == false )
	// 		throw new Exception("Connection not activated...");

	// 	if( studentDAO == null )
	// 		studentDAO = new StudentDAO_JDBC( dbconnection );

	// 	return studentDAO;
    // }
    
    public CustomerDAO GetCustomerDAO () throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( customer_dao == null )
            customer_dao = new CustomerDAO_JDBC( dbconnection );

		return customer_dao;
    }
    
    public SellerDAO GetSellerDAO () throws Exception
    {
        if( activeConnection == false )
            throw new Exception("Connection not activated...");

        if( seller_dao == null )
            seller_dao = new SellerDAO_JDBC( dbconnection );

        return seller_dao;
    }

    public ProductDAO GetProductDAO () throws Exception
    {
        if( activeConnection == false )
            throw new Exception("Connection not activated...");

        if( product_dao == null )
            product_dao = new ProductDAO_JDBC( dbconnection );

        return product_dao;
	}
	
	public UserDAO GetUserDAO () throws Exception
    {
        if( activeConnection == false )
            throw new Exception("Connection not activated...");

        if( user_dao == null )
            user_dao = new UserDAO_JDBC( dbconnection );

        return user_dao;
    }
	
	public ComplaintDAO GetComplaintDAO () throws Exception
    {
        if( activeConnection == false )
            throw new Exception("Connection not activated...");

        if( complaint_dao == null )
            complaint_dao = new ComplaintDAO_JDBC ( dbconnection );

        return complaint_dao;
	}

	public TOrderDAO GetTOrderDAO () throws Exception
    {
        if( activeConnection == false )
            throw new Exception("Connection not activated...");

        if( tOrder_dao == null )
			tOrder_dao = new TOrderDAO_JDBC ( dbconnection );

        return tOrder_dao;
	}

	public OrderItemDAO GetOrderItemDAO () throws Exception
    {
        if( activeConnection == false )
            throw new Exception("Connection not activated...");

        if( order_item_dao == null )
			order_item_dao = new OrderItemDAO_JDBC ( dbconnection );

        return order_item_dao;
	}

	public TReturnsDAO GetTReturnsDAO () throws Exception
    {
        if( activeConnection == false )
            throw new Exception("Connection not activated...");

        if( tReturns_dao == null )
			tReturns_dao = new TReturnsDAO_JDBC ( dbconnection );

        return tReturns_dao;
	}
	
	public void deactivateConnection()
	{
		// Okay to keep deactivating an already deactivated connection
		activeConnection = false;
		if( dbconnection != null ){
			try{
				dbconnection.close();
				dbconnection = null;
				// studentDAO = null;
			}
			catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
	}
};

