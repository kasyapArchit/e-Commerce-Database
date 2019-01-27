package cstate;

import daofactory.DAO_Factory;

import java.util.ArrayList;

import beans.*;
import utils.OItem;

public class CState {
    DAO_Factory dao_factory;
    User current_user = null;
    Seller current_seller = null;
    Customer current_customer = null;
    Long current_product_id;
    Long complaint_id;
    Long order_id;
    Long order_item_id;
    ArrayList<OItem> cart; 

    public CState () {
        this.dao_factory = new DAO_Factory();
        this.cart = new ArrayList<OItem>();
        try {
            this.dao_factory.activateConnection();
        } catch (Exception e) {
            System.out.println (e.getMessage());
        }
    }
    public void AddToCart (Long product_id, int quantity) {
        cart.add (new OItem (product_id, quantity));
    }
    public void Reset () {
        current_user = null;
        current_seller = null;
        current_customer = null;
        order_id = null;
        order_item_id = null;
        current_product_id = Long.valueOf (-1);
        cart = new ArrayList <OItem> ();
    }
    public void SetUser (User user) {
        this.current_user = user;
    }
    public void SetSeller (Seller seller) {
        this.current_seller = seller;
    }
    public void SetCustomer (Customer customer) {
        this.current_customer = customer;
    }
    public void SetProductID (Long product_id) {
        this.current_product_id = product_id;
    }
    public void SetComplaintID (Long complaint_id) {
        this.complaint_id = complaint_id;
    }
    public void SetOrderID (Long order_id){this.order_id=order_id;}
    public void SetOrderItemID (Long order_item_id){this.order_item_id=order_item_id;}
    public Long GetOrderID (){return this.order_id;}
    public Long GetOrderItemID (){return this.order_item_id;}
    public User GetUser () {
        return this.current_user;
    }
    public Seller GetSeller () {
        return this.current_seller;
    }
    public Customer GetCustomer () {
        return this.current_customer;
    }
    public Long GetProductID () {
        return this.current_product_id;
    }
    public Long GetComplaintID () {
        return this.complaint_id;
    }
    public ArrayList<OItem> GetCart () {
        return this.cart;
    }
    public DAO_Factory GetDAOFactory () {
        return this.dao_factory;
    }
}