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

public class CustomerOrderItemView implements View{
    Cresponse m_cresponse;
    CState m_current_state;
    ArrayList<OrderItem> order_item_list;

    public CustomerOrderItemView (CState current_state) {this.m_current_state = current_state;}
    @Override
    public void Display(){
        try {
            order_item_list = m_current_state.GetDAOFactory().GetOrderItemDAO().GetProductByOrderId(this.m_current_state.GetOrderID());
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }

        if (order_item_list == null)
        {
            System.out.println ("No such order available, please enter valid order id by going to order view");
            m_cresponse = new Cresponse (0);
            return ;
        }
        else
        {
            System.out.println ("OrderItemID\t ProductID\t Amount\t Quantity\t Status");
            for (OrderItem o : order_item_list)
            {
                System.out.println (o.get_order_item_id()+"\t"+o.get_product_id()+"\t"+o.get_amount()+"\t"+o.get_quantity()+"\t"+o.get_stat());
            }
        }


        System.out.println ("Customer Order Item :");
        System.out.println ("1. Refund(or Cancel) Order");
        System.out.println ("2. Return(or Replacement) Order");
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
                System.out.print ("Enter order item id: ");
                Scanner sc = new Scanner (System.in);
                Long response = sc.nextLong();
                this.m_current_state.SetOrderItemID(response);
                return (new CustomerOrderItemReturnView(this.m_current_state));
            }
            case 1:{
                System.out.print ("Enter order item id: ");
                Scanner sc = new Scanner (System.in);
                Long response = sc.nextLong();
                this.m_current_state.SetOrderItemID(response);
                return (new CustomerOrderItemRefundView(this.m_current_state));
            }
            case 0: return (new CustomerHomeView(m_current_state));
        }
        return (new CustomerHomeView(m_current_state));
    }
}