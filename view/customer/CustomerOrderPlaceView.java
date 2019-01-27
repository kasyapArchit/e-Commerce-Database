package view.customer;

import beans.*;
import cresponse.Cresponse;
import view.LoginView;
import view.View;
import cstate.CState;
import utils.*;
import view.customer.*;
import dao.*;
import java.util.ArrayList;
import utils.OItem;
import java.util.Scanner;

public class CustomerOrderPlaceView implements View{
    Cresponse m_cresponse;
    CState m_current_state;
    TOrder order;
    Customer cust;
    ArrayList<OItem> cart;

    public CustomerOrderPlaceView (CState current_state) {
        this.m_current_state = current_state;
        this.cart = this.m_current_state.GetCart();
        this.cust = this.m_current_state.GetCustomer();
        this.order = new TOrder();
    }
    @Override
    public void Display(){
        Scanner sc = new Scanner (System.in);

        this.order.set_customer_mobile_no(cust.get_mobile_no());
        this.order.set_delivery_address(cust.get_address_line_1()+cust.get_address_line_2());

        // get product and decrease it's quantity, calculate total_amount
        float total_amount = 0;
        for(OItem itm : cart){
            Long pid = itm.GetProductID();
            int quan = itm.GetQuantity();
            
            try {
                ProductDAO product_dao = m_current_state.GetDAOFactory().GetProductDAO ();
                Product product = product_dao.GetProductByProductId(pid);
                total_amount += (product.get_price()-product.get_offer())*quan;
            } catch (Exception e) {
                System.out.println (e.getMessage());
            }
        }
        this.order.set_total_amount(total_amount);
        System.out.println("Total amount = "+total_amount+" Do you want to place order (0=>No,1=>Yes)");
        System.out.print ("Response: ");
        int response = sc.nextInt();
        if(response==0){
            m_cresponse = new Cresponse(response);
            return ;
        }
        // place order
        try {
            TOrderDAO torder_dao = m_current_state.GetDAOFactory().GetTOrderDAO();
            this.order.set_order_id(torder_dao.GetNewId());
            this.m_current_state.SetOrderID(this.order.get_order_id());
            torder_dao.AddTOrder(order);
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }

        // add order items
        for(OItem itm: cart){
            Long pid = itm.GetProductID();
            int quan = itm.GetQuantity();
            Product product=null;
            try {
                ProductDAO product_dao = m_current_state.GetDAOFactory().GetProductDAO ();
                product = product_dao.GetProductByProductId(pid);
                product.set_available_quantity(product.get_available_quantity()-quan);
                product_dao.UpdateProduct(product);
            } catch (Exception e) {
                System.out.println (e.getMessage());
            }
            float amnt = product.get_price()-product.get_offer();
            int stat = 0;
            try {
                OrderItemDAO order_item_dao = m_current_state.GetDAOFactory().GetOrderItemDAO();
                Long id = order_item_dao.GetNewId();
                OrderItem order_item = new OrderItem(id, this.order.get_order_id(), pid, amnt, quan,stat); 
                order_item_dao.AddOrderItem(order_item);
            } catch (Exception e) {
                System.out.println (e.getMessage());
            }
        }
        // order placed
        System.out.println("Your Order Id = "+this.order.get_order_id());
        
        System.out.println ("Customer Order :");
        System.out.println ("1. View Orders");
        System.out.println ("2. Back to Home");
        System.out.print ("Response: ");
        int resp = sc.nextInt();
        m_cresponse = new Cresponse (resp);
        // if(sc!=null) sc.close();
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
            case 1: return (new CustomerAllOrderView (m_current_state));
        }
        return (new CustomerHomeView(m_current_state));
    }
}