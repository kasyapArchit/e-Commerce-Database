package view.seller;

import beans.*;
import cresponse.Cresponse;
import view.View;
import view.LoginView;
import cstate.CState;

import java.util.Scanner;
import java.util.ArrayList;

public class UpdateProductView implements View {
    Cresponse m_cresponse;
    CState m_current_state;

    public UpdateProductView (CState current_state) {this.m_current_state = current_state;}

    @Override
    public void Display () {
        System.out.println ("Select Product: ");
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
        System.out.println ("ID\tName\tQuantity\toffer");
        for (Product p : product_list)
        {
            System.out.println (p.get_product_id()+"\t"+p.get_product_name()+"\t"+p.get_available_quantity()+
            "\t"+p.get_offer());
        }
        Scanner sc = new Scanner (System.in);
        System.out.print ("Enter Product ID: ");

        Long product_id = sc.nextLong();

        m_current_state.SetProductID (product_id);
        /* UPDATE PRODUCT !!*/
        
        m_cresponse = new Cresponse (0);
    }

    @Override
    public Cresponse GetResponse () {
        return m_cresponse;
    }

    @Override
    public View GetNextView () {
        return (new ProductView (m_current_state));
    }
}