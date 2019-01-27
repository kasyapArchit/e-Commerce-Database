package view.seller;

import cresponse.Cresponse;
import view.View;
import view.seller.ManageProductsView;
import view.seller.SellerHomeView;
import view.LoginView;
import cstate.CState;
import beans.Product;

import java.util.Scanner;

public class AddNewProductView implements View {
    Cresponse m_cresponse;
    CState m_current_state;

    public AddNewProductView (CState current_state) {this.m_current_state = current_state;}

    @Override
    public void Display () {
        System.out.println ("Add New Product: ");
        // Name
        // Price
        // quantity
        Scanner sc = new Scanner (System.in);
        System.out.println ("Product Name: ");
        String product_name = sc.next();
        
        System.out.println ("Product Price: ");
        float price = sc.nextFloat();

        System.out.println ("Product Quantity: ");
        int quantity = sc.nextInt();

        System.out.println ("Product Offer: ");
        float offer = sc.nextFloat ();
        
        try {
            m_current_state.GetDAOFactory().GetProductDAO().AddProduct (new Product (
                offer,
                quantity,
                new Long(0),
                m_current_state.GetUser().get_mobile_no (),
                product_name,
                price
            ));
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }

        m_cresponse = new Cresponse (0);
    }

    @Override
    public Cresponse GetResponse () {
        return m_cresponse;
    }

    @Override
    public View GetNextView () {
        return (new ManageProductsView (m_current_state));
    }
}