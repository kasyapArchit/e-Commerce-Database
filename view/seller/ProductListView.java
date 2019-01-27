package view.seller;

import beans.*;
import cresponse.Cresponse;
import view.View;
import view.LoginView;
import cstate.CState;

import java.util.Scanner;
import java.util.ArrayList;

public class ProductListView implements View {
    Cresponse m_cresponse;
    CState m_current_state;

    public ProductListView (CState current_state) {this.m_current_state = current_state;}

    @Override
    public void Display () {
        System.out.println ("Your Products: ");
        ArrayList <Product> product_list = null;
        try {
            product_list = m_current_state.GetDAOFactory().GetProductDAO ().GetProductBySeller(
                m_current_state.GetUser().get_mobile_no()
            );


        } catch (Exception e) {
            System.out.println (e.getMessage());
        }
        // Name
        // Price
        // quantity
        if (product_list == null)
        {
            System.out.println ("You don't have any products");
        }
        else
        {
            System.out.println ("ID\tName\tQuantity\toffer");
            for (Product p : product_list)
            {
                System.out.println (p.get_product_id()+"\t"+p.get_product_name()+"\t"+p.get_available_quantity()+
                "\t"+p.get_offer());
            }
        }

        System.out.println ("Options: ");
        System.out.println ("1. Go Back");
        System.out.println ("2. Select Product");

        Scanner sc = new Scanner (System.in);
        int resp = sc.nextInt();


        /* UPDATE PRODUCT !!*/
        
        m_cresponse = new Cresponse (resp);
        if (sc!=null) sc.close();
    }

    @Override
    public Cresponse GetResponse () {
        return m_cresponse;
    }

    @Override
    public View GetNextView () {
        Object obj = m_cresponse.GetObject ();
        switch ((int)obj) {
            case 1 : return (new SellerHomeView (m_current_state));
            case 2 : {
                System.out.print ("Enter ProductID: ");
                Scanner sc = new Scanner (System.in);
                Long product_id = sc.nextLong ();
                m_current_state.SetProductID(product_id);


                return (new ProductView (m_current_state));
            }
        }
        return null;
    }
}