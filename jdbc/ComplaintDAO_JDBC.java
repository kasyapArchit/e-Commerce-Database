package jdbc;

import beans.Complaint;
import dao.ComplaintDAO;

import java.sql.*;
import java.util.ArrayList;


public class ComplaintDAO_JDBC implements ComplaintDAO {

    Connection dbConnection;

    public ComplaintDAO_JDBC(Connection dbconn){
        dbConnection = dbconn;
    }

    @Override
    public void AddComplaint (Complaint complaint) {
        PreparedStatement preparedStatement = null;																																																																																																																																													
        String sql;
        sql = "insert into Complaint (comp_subject, comp, stat, mobile_no)"+
            " values (?, ?, ?, ?)";

        try {
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setString   (1, complaint.get_subject ());
            preparedStatement.setString (2, complaint.get_text ());
            preparedStatement.setBoolean (3, complaint.get_status () );
            preparedStatement.setLong (4, complaint.get_mobile_no());

            // execute insert SQL stetement
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

    @Override
    public Complaint GetComplaintByID (Long complaint_id) {
        Complaint complaint = null;
        String sql;
        Statement stmt = null;

        try{
            stmt = dbConnection.createStatement();
            sql = "select * from Complaint where complaint_id = " + complaint_id;
            ResultSet rs = stmt.executeQuery(sql);
                                                   
            while(rs.next()) {
                String subject = rs.getString ("comp_subject");
                String text = rs.getString ("comp");
                Long mobile_no = rs.getLong ("mobile_no");
                Boolean stat = rs.getBoolean ("stat");
                

                complaint = new Complaint (subject, text, stat, mobile_no);

                break;
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        // Add exception handling when there is no matching record
        return complaint;
    }

    @Override
    public void Resolve (Long complaint_id) {
        Complaint complaint = null;
        String sql;
        PreparedStatement preparedStatement = null;
        sql = "update Complaint set stat = ? where complaint_id = " + complaint_id;

        try {
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
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