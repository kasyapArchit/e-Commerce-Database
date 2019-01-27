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
import beans.*;

public class CustomerOrderItemReturnView implements View{
    Cresponse m_cresponse;
    CState m_current_state;

    public CustomerOrderItemReturnView(CState current_state) {this.m_current_state = current_state;}
    @Override
    public void Display(){
        OrderItem order_item=null;
        try {
            order_item = m_current_state.GetDAOFactory().GetOrderItemDAO().GetProductByOrderItemId(this.m_current_state.GetOrderItemID());
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }

        if (order_item == null)
        {
            System.out.println ("No such order item available, please enter valid order item id by going to order view");
            m_cresponse = new Cresponse (0);
            return ;
        }
        else if(order_item.get_stat()!=0){
            System.out.println("Sorry, this item cannot be replaced.");
            m_cresponse = new Cresponse (0);
            return ;
        }
        order_item.set_stat(0);
        try {
            m_current_state.GetDAOFactory().GetOrderItemDAO().UpdateOrderItem(order_item);
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }

        System.out.println("Replacement is placed under the same order id and order item id");
        
        System.out.println ("Customer Order Item Replace:");
        System.out.println ("1. Back to Order");
        System.out.println ("2. Back to Home");

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
            case 2: return (new CustomerHomeView (m_current_state));
            case 1: return (new CustomerOrderView (m_current_state));
            case 0: return (new CustomerOrderView(m_current_state));
        }
        return (new CustomerHomeView(m_current_state));
    }
}