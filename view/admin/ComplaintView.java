package view.admin;

import beans.*;
import cresponse.*;
import cstate.*;
import dao.*;
import daofactory.DAO_Factory;
import view.View;

import java.util.Scanner;

public class ComplaintView implements View {
    Cresponse m_cresponse = null;
    CState m_current_state = null;

    public ComplaintView (CState state) {
        this.m_current_state = state;
    }

    @Override
    public void Display () {
        DAO_Factory dao_factory = m_current_state.GetDAOFactory ();
        try {

            ComplaintDAO complaint_dao = dao_factory.GetComplaintDAO ();
            Complaint complaint = complaint_dao.GetComplaintByID (m_current_state.GetComplaintID ());
    
            if (complaint == null)
            {
                System.out.println ("No such complaint");
                m_cresponse = new Cresponse (0);
            }
            else
            {
                System.out.println (complaint.toString());
                System.out.println ("Options: ");
                System.out.println ("1. Resolve");
                System.out.println ("2. Back to ComplaintList");
                
                System.out.print ("Response: ");
                Scanner sc = new Scanner (System.in);
                int resp = sc.nextInt ();
                m_cresponse = new Cresponse (resp);
            }
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }
        
    }

    @Override
    public Cresponse GetResponse () {
        return m_cresponse;
    }

    @Override
    public View GetNextView () {
        Object obj = m_cresponse.GetObject ();
        switch ((int) obj)
        {
            case 2: return (new ComplaintListView (m_current_state));
            case 1: {
                try {
                    m_current_state.GetDAOFactory().GetComplaintDAO().Resolve (
                        m_current_state.GetComplaintID()
                    );
                } catch (Exception e) {
                    System.out.println (e.getMessage());
                }
                return (new ComplaintListView (m_current_state));
            }
        }
        return (new ComplaintListView (m_current_state));
    }
}