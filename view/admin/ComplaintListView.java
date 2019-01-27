package view.admin;

import java.util.Scanner;
import view.View;
import view.customer.CustomerHomeView;
import cresponse.Cresponse;
import dao.ProductDAO;
import cstate.CState;

public class ComplaintListView implements View {
    Cresponse m_cresponse = null;
    CState m_current_state = null;

    public ComplaintListView (CState state) {this.m_current_state = state;}

    @Override
    public void Display () {
        System.out.println ("List of Complaints: ");
        System.out.println ("CompalintID\t \t Subject\t Resolved?");

        System.out.println ("Options: ");
        System.out.println ("1. Select Complaint");
        System.out.println ("2. Back to Home");
        
        System.out.print ("Response: ");
        Scanner sc = new Scanner (System.in);
        int resp = sc.nextInt ();
        m_cresponse = new Cresponse (resp);
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
            case 2: return (new AdminHomeView (m_current_state));
            case 1: {
                System.out.print ("Enter complaint id: ");
                Scanner sc = new Scanner (System.in);
                Long resp = sc.nextLong ();
                m_current_state.SetComplaintID(resp);

                return (new ComplaintView (m_current_state));
            }
        }
        return (new AdminHomeView (m_current_state));
    }
}