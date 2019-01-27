package view.customer;

import cresponse.Cresponse;
import view.LoginView;
import view.View;
import cstate.CState;

import java.util.Scanner;

public class CustomerHomeView implements View {
    Cresponse m_cresponse;
    CState m_current_state;

    public CustomerHomeView (CState current_state) {this.m_current_state = current_state;}
    @Override
    public void Display () {
        System.out.println ("Customer Home :");
        System.out.println ("1. View Products");
        System.out.println ("2. View Orders");
        System.out.println ("3. View Cart");
        System.out.println ("4. Add Complaint");
        System.out.println ("5. Logout");

        System.out.print ("Response: ");
        Scanner sc = new Scanner (System.in);
        int response = sc.nextInt();
        m_cresponse = new Cresponse (response);
        // if (sc!=null) sc.close();
    }

    @Override
    public Cresponse GetResponse () {
        return m_cresponse;
    }

    @Override
    public View GetNextView () {
        Object obj = m_cresponse.GetObject();
        switch ((int)obj) {
            case 5: {
                m_current_state.Reset ();
                return (new LoginView (m_current_state));
            }
            case 4 : return (new CustomerAddComplaintView (m_current_state));
            case 3: return (new CartView (m_current_state));
            case 2: return (new CustomerOrderView (m_current_state));
            case 1 : return (new ProductListView (m_current_state));
        }

        return (new CustomerHomeView(m_current_state));
    }
}