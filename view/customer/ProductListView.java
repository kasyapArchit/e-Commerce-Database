package view.customer;

import beans.*;
import java.util.Scanner;
import view.View;
import view.customer.*;
import cresponse.Cresponse;
import dao.*;
import cstate.CState;
import java.util.ArrayList;

public class ProductListView implements View {
    Cresponse m_cresponse = null;
    CState m_current_state = null;

    public ProductListView (CState state) {this.m_current_state = state;}
    @Override
    public void Display () {
        System.out.println ("List of Products: ");
        
        ArrayList<Product> product_list=null;
        try {
            product_list = m_current_state.GetDAOFactory().GetProductDAO().GetAll();
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }

        if (product_list == null)
        {
            System.out.println ("Sorry no products available");
            m_cresponse = new Cresponse (0);
            return;
        }
        else
        {
            System.out.println ("ProductID\t ProductName\t Quantity\t Offer\t Price");
            for (Product p : product_list)
            {
                System.out.println (p.get_product_id()+"\t"+p.get_product_name()+"\t"+p.get_available_quantity()+"\t"+p.get_offer()+"\t"+p.get_price());
            }
        }
        System.out.println ("Options: ");
        System.out.println ("1. Select Product");
        System.out.println ("2. Back to Home");
        System.out.print   ("Response: ");
        Scanner sc = new Scanner (System.in);
        int resp = sc.nextInt ();
        m_cresponse = new Cresponse (resp);
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
            case 1: {
                System.out.print ("Enter product id: ");
                Scanner sc = new Scanner (System.in);
                Long resp = sc.nextLong ();
                m_current_state.SetProductID (resp);
                // if(sc!=null) sc.close();
                return (new ProductView (m_current_state));
            }
        }
        return (new CustomerHomeView(m_current_state));
    }
}