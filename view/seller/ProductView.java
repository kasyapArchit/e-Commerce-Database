package view.seller;

import beans.*;
import cresponse.Cresponse;
import view.View;
import view.LoginView;
import cstate.CState;

import java.util.Scanner;
import java.util.ArrayList;

public class ProductView implements View {
    Cresponse m_cresponse;
    CState m_current_state;

    public ProductView (CState current_state) {this.m_current_state = current_state;}

    @Override
    public void Display () {
        Product product = null;
        try {
            product = m_current_state.GetDAOFactory().GetProductDAO ().GetProductByProductIdAndSeller(
                m_current_state.GetProductID (), m_current_state.GetUser ().get_mobile_no ());

        } catch (Exception e) {
            System.out.println (e.getMessage());
        }
        // Name
        // Price
        // quantity
        if (product == null)
        {
            System.out.println ("No such product");
        }
        System.out.println ("ID\tName\tQuantity\tPrice(after offer)");
        System.out.println (product.get_product_id()+"\t"+product.get_product_name()+"\t"+product.get_available_quantity()+
        "\t"+(product.get_price()-product.get_offer()));


        System.out.println ("Options: ");
        System.out.println ("1. Update Product");
        System.out.println ("2. Delete Product");

        Scanner sc = new Scanner (System.in);
        int resp = sc.nextInt();


        /* UPDATE PRODUCT !!*/
        
        m_cresponse = new Cresponse (resp);
    }

    @Override
    public Cresponse GetResponse () {
        return m_cresponse;
    }

    @Override
    public View GetNextView () {
        Object obj = m_cresponse.GetObject ();
        switch ((int)obj) {
            case 1 : {
                System.out.print ("Enter ProductID: ");
                Scanner sc = new Scanner (System.in);
                Long product_id = sc.nextLong ();

                System.out.println ("Product Name: ");
                String product_name = sc.next();
                
                System.out.println ("Product Price: ");
                float price = sc.nextFloat();

                System.out.println ("Product Quantity: ");
                int quantity = sc.nextInt();

                System.out.println ("Product Offer: ");
                float offer = sc.nextFloat ();
                
                try {
                    m_current_state.GetDAOFactory ().GetProductDAO ().UpdateProduct (
                        new Product (offer,
                        quantity,
                        product_id,
                        m_current_state.GetUser ().get_mobile_no (),
                        product_name,price)
                    );
                } catch (Exception e) {
                    System.out.println (e.getMessage ());
                }
                
                return (new SellerHomeView (m_current_state));
            }
            case 2 : {
                System.out.print ("Enter ProductID: ");
                Scanner sc = new Scanner (System.in);
                Long product_id = sc.nextLong ();
                try {
                    m_current_state.GetDAOFactory ().GetProductDAO ().DeleteProduct (
                        m_current_state.GetDAOFactory ().GetProductDAO ().GetProductByProductIdAndSeller (
                            m_current_state.GetUser ().get_mobile_no (), product_id));
                } catch (Exception e) {
                    System.out.println (e.getMessage ());
                }

                return (new ProductListView (m_current_state));
            }
        }
        return null;
    }
}