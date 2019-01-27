package view.customer;

import beans.*;
import cresponse.Cresponse;
import cstate.CState;
import dao.ProductDAO;
import daofactory.DAO_Factory;
import view.View;
import view.customer.ProductListView;

import java.util.Scanner;

public class ProductView implements View {
    Cresponse m_cresponse = null;
    CState m_current_state = null;
    Product product = null;

    public ProductView (CState state) {
        this.m_current_state = state;
    }
    @Override
    public void Display () {
        DAO_Factory dao_factory = m_current_state.GetDAOFactory ();
        try {

            ProductDAO product_dao = dao_factory.GetProductDAO ();
            product = product_dao.GetProductByProductId (m_current_state.GetProductID ());
    
            if (product == null)
            {
                System.out.println ("No such product");
                m_cresponse = new Cresponse (0);
            }
            else
            {
                // System.out.println ("Product Name: "+product.get_product_name()+", Price: "+product.get_price());
                System.out.println("Options: ");
                System.out.println("1. Add To Cart");
                System.out.println("2. Back to ProductList");
                System.out.print ("Response: ");
                Scanner sc = new Scanner (System.in);
                int resp = sc.nextInt ();
                m_cresponse = new Cresponse (resp);
                // if(sc!=null) sc.close();
            }
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }
        
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
            case 2: return (new ProductListView (m_current_state));
            case 1: {
                int quantity=1000000;
                Scanner sc = new Scanner (System.in);
                while(quantity>product.get_available_quantity())
                {
                    System.out.print ("Enter Quantity (less than "+product.get_available_quantity()+"): ");
                    quantity = sc.nextInt();
                }
                m_current_state.AddToCart(product.get_product_id(), quantity);
                System.out.println("Product added to cart");
                // if(sc!=null) sc.close();
                return (new ProductListView (m_current_state));
            }
            case 0: return (new ProductListView (m_current_state)); 
        }
        return null;
    }
}