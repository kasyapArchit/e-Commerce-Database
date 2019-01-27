package view.seller;

import cresponse.Cresponse;
import view.View;
import view.LoginView;
import cstate.CState;

import java.util.Scanner;

public class SellerHomeView implements View {
    Cresponse m_cresponse;
    CState m_current_state;

    public SellerHomeView (CState current_state) {this.m_current_state = current_state;}

    @Override
    public void Display () {
        System.out.println ("Seller Home :");
        System.out.println ("1. Manage Products");
        System.out.println ("2. Add Complaint");
        System.out.println ("3. Logout");

        System.out.print ("Response :");
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
            case 3 : return (new LoginView (m_current_state));
            case 2 : return (new SellerAddComplaintView (m_current_state));
            case 1 : return (new ManageProductsView (m_current_state) );
        }
        return (new SellerHomeView(m_current_state));
    }
}