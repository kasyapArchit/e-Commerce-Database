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

public class CustomerAllOrderView implements View{
    Cresponse m_cresponse;
    CState m_current_state;
    ArrayList<TOrder> all_order_list;

    public CustomerAllOrderView (CState current_state) {this.m_current_state = current_state;}
    @Override
    public void Display(){
        try {
            all_order_list = m_current_state.GetDAOFactory().GetTOrderDAO().GetOrderByUserId(this.m_current_state.GetCustomer().get_mobile_no());
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }

        if (all_order_list == null)
        {
            System.out.println ("You have made no orders");
            m_cresponse = new Cresponse (0);
            return ;
        }
        else
        {
            System.out.println("OrderId\t Total Amount\t Delivery Address");
            for (TOrder o : all_order_list)
            {
                System.out.println (o.get_order_id()+"\t"+o.get_total_amount()+"\t"+o.get_delivery_address());
            }
        }


        System.out.println ("Customer All Orders :");
        System.out.println ("1. View Order Details");
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
            case 1:{
                System.out.print("Enter order id:");
                Scanner sc = new Scanner (System.in);
                Long id = sc.nextLong();
                this.m_current_state.SetOrderID(id);
                // if(sc!=null) sc.close();
                return (new CustomerOrderItemView (m_current_state));
            } 
            case 0: return (new CustomerHomeView(m_current_state));
        }
        return (new CustomerHomeView(m_current_state));
    }
}