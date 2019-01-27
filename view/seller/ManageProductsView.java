package view.seller;

import cresponse.Cresponse;
import view.View;
import view.LoginView;
import cstate.CState;

import java.util.Scanner;

public class ManageProductsView implements View {
    Cresponse m_cresponse;
    CState m_current_state;

    public ManageProductsView (CState current_state) {this.m_current_state = current_state;}
    
    @Override
    public void Display () {
        System.out.println ("Manage Products: ");
        System.out.println ("1. Add New Product");
        System.out.println ("2. Update Product");
        System.out.println ("3. Home");
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
            case 2: return (new UpdateProductView (m_current_state));
            case 1: return (new AddNewProductView (m_current_state));
        }
        return (new SellerHomeView (m_current_state));
    }
}