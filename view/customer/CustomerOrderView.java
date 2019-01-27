package view.customer;

import cresponse.Cresponse;
import view.LoginView;
import view.View;
import cstate.CState;
import utils.*;
import view.customer.*;
import java.util.ArrayList;
import utils.OItem;
import java.util.Scanner;

public class CustomerOrderView implements View{
    Cresponse m_cresponse;
    CState m_current_state;

    public CustomerOrderView (CState current_state) {this.m_current_state = current_state;}
    @Override
    public void Display(){
        System.out.println ("Customer Home :");
        System.out.println ("1. View Past Orders");
        System.out.println ("2. Buy items in Cart");
        System.out.println ("3. Back to Home");

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
        Object obj = m_cresponse.GetObject ();
        switch ((int) obj)
        {
            case 3: return (new CustomerHomeView (m_current_state));
            case 2:{
                ArrayList<OItem> cart = m_current_state.GetCart();
                if(cart.isEmpty()){
                    System.out.println("Your cart is empty, please add items in your cart and then place order.");
                    return (new CustomerHomeView (m_current_state));
                }

                return (new CustomerOrderPlaceView (m_current_state));
            } 
            case 1: return (new CustomerAllOrderView (m_current_state));
        }
        return (new CustomerHomeView(m_current_state));
    }
}