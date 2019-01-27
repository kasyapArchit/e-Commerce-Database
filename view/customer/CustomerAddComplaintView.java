package view.customer;

import beans.Complaint;
import cresponse.Cresponse;
import cstate.CState;
import view.View;
import view.customer.*;
import view.seller.*;

import java.util.Scanner;


public class CustomerAddComplaintView implements View {
    private Cresponse m_response = null;
    private CState m_current_state = null;

    public CustomerAddComplaintView (CState current_state) {this.m_current_state = current_state;}
    
    @Override
    public void Display () {
        System.out.println ("Dear Customer, enter your Grievance here: ");
        
        System.out.print ("Subject: ");
        Scanner sc = new Scanner (System.in);
        String subject = sc.nextLine();
        System.out.print ("Complaint: ");
        String comp = sc.nextLine();
        try {
            m_current_state.GetDAOFactory().GetComplaintDAO().AddComplaint (
                new Complaint (subject, comp, false, m_current_state.GetUser ().get_mobile_no())
            );
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }
    }

    @Override
    public Cresponse GetResponse () {
        return m_response;
    }

    @Override
    public View GetNextView () {
        return  (new CustomerHomeView (m_current_state));
    }
}