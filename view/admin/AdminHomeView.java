package view.admin;

import cresponse.Cresponse;
import view.LoginView;
import view.View;
import cstate.CState;

import java.util.Scanner;

public class AdminHomeView implements View {
    Cresponse m_cresponse;
    CState m_current_state;

    public AdminHomeView (CState current_state) {this.m_current_state = current_state;}
    @Override
    public void Display () {
        System.out.println ("Admin Home: ");
        System.out.println ("1. View Complaints");
        System.out.println ("2. View Requests");
        System.out.println ("3. Logout");

        System.out.print ("Response: ");
        Scanner sc = new Scanner (System.in);
        int response = sc.nextInt();
        m_cresponse = new Cresponse (response);
    }

    @Override
    public Cresponse GetResponse () {
        return m_cresponse;
    }

    @Override
    public View GetNextView () {
        Object obj = m_cresponse.GetObject();
        switch ((int)obj) {
            case 3: {
                m_current_state.Reset ();
                return (new LoginView (m_current_state));
            }
            case 1 : return (new ComplaintListView (m_current_state));
        }

        return (new AdminHomeView (m_current_state));
    }
}