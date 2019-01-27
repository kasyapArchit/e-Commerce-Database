package view;

import beans.*;
import cstate.CState;
import dao.*;
import daofactory.DAO_Factory;
import cresponse.Cresponse;
import view.View;
import view.customer.*;
import view.seller.*;
import view.admin.*;

import java.util.Scanner;


public class LoginView implements View {
    private Cresponse m_response = null;
    private CState m_current_state = null;

    public LoginView (CState current_state) {this.m_current_state = current_state;}
    @Override
    public void Display () {
        System.out.println ("Login as:");
        System.out.println ("1. Admin");
        System.out.println ("2. Seller");
        System.out.println ("3. Customer");
        System.out.println ("4. Exit");

        System.out.print ("Response: ");
        Scanner sc = new Scanner (System.in);
        int response = sc.nextInt();
        m_response = new Cresponse (response);
        // if(sc != null) sc.close();
    }

    @Override
    public Cresponse GetResponse () {
        return m_response;
    }

    @Override
    public View GetNextView () {
        Object obj = m_response.GetObject();
        switch ((int)obj) {
            case 4: return null;
            case 3: {
                Login ();
                if (m_current_state.GetUser () == null) {return (new LoginView (m_current_state));}

                DAO_Factory dao_factory = m_current_state.GetDAOFactory ();

                try {
                    CustomerDAO customer_dao = dao_factory.GetCustomerDAO ();
                    Customer customer = customer_dao.GetCustomerByUser (m_current_state.GetUser ());
                    m_current_state.SetCustomer (customer);
                    if (customer == null)
                    {
                        System.out.println ("Invalid Customer");
                        m_current_state.SetUser (null);
                        return (new LoginView (m_current_state));
                    }
                } catch (Exception e) {
                    System.out.println (e.getMessage());
                }
                // return (new LoginView(m_current_state));
                return (new CustomerHomeView (m_current_state));
            }
            case 2: {
                Login ();
                if (m_current_state.GetUser () == null) {return (new LoginView (m_current_state));}
                System.out.println ("Logged User in..");
                DAO_Factory dao_factory = m_current_state.GetDAOFactory ();

                try {
                    SellerDAO seller_dao = dao_factory.GetSellerDAO ();
                    Seller seller = seller_dao.GetSellerByUser (m_current_state.GetUser ());
                    m_current_state.SetSeller(seller);
                    if (seller == null)
                    {
                        System.out.println ("Invalid Seller");
                        m_current_state.SetUser (null);
                        return (new LoginView (m_current_state));
                    }

                } catch (Exception e) {
                    System.out.println (e.getMessage());
                }

                return (new SellerHomeView (m_current_state));
            }

            case 1: {
                return (new AdminHomeView (m_current_state));
            }
        }
        return null;
    }

    public void Login () {
        Scanner sc = new Scanner (System.in);
        System.out.print ("Enter mobile no: ");
        Long mobile_no = sc.nextLong ();
        System.out.print ("Enter password: ");
        String password = "password"; //sc.next ();

        DAO_Factory dao_factory = m_current_state.GetDAOFactory ();
        UserDAO user_dao = null;
        User user = null;
        try {
            user_dao = dao_factory.GetUserDAO ();
            user = user_dao.GetUser (mobile_no, password);
            m_current_state.SetUser (user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user == null) {
            System.out.println ("User is null");
        }
        // if(sc != null)  sc.close();
    }
}