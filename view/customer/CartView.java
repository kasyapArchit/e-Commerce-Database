package view.customer;

import cresponse.*;
import cstate.*;
import view.View;
import view.customer.*;
import utils.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CartView implements View {
    Cresponse m_cresponse = null;
    CState m_current_state;
    
    public CartView (CState current_state) {this.m_current_state = current_state;}

    @Override
    public void Display () {
        ArrayList <OItem> cart = m_current_state.GetCart ();
        if (cart == null || cart.isEmpty()) {
            System.out.println ("Cart is Empty.");
        }
        else {
            for (OItem oi : cart ) {
                System.out.println ("ProductId\t Quantity");
                System.out.println (oi.GetProductID() + "\t" + oi.GetQuantity());
            }
        }
        System.out.println ("Options :");
        System.out.println ("1. Checkout.");
        System.out.println ("2. Back To Home");
        System.out.print ("Response: ");
        Scanner sc = new Scanner (System.in);
        int resp = sc.nextInt();
        // if(sc!=null) sc.close();
        m_cresponse = new Cresponse (resp);
    }
    
    @Override
    public Cresponse GetResponse () {
        return this.m_cresponse;
    }
    @Override
    public View GetNextView () {
        Object resp = m_cresponse.GetObject ();
        switch ((int) resp) {
            case 2: return (new CustomerHomeView (m_current_state));
            case 1:{
                ArrayList <OItem> cart = m_current_state.GetCart ();
                if(cart.isEmpty()){
                    System.out.println("Please add items in cart and then place order");
                    return (new ProductListView(this.m_current_state));
                }
                return (new CustomerOrderPlaceView(this.m_current_state));
            }
        }

        return (new CustomerHomeView (this.m_current_state));
    }
}