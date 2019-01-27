package dao;

import beans.Seller;
import beans.User;

public interface SellerDAO {
    // public Seller GetSellerByMobileNo (Long mobile_no);
    public Seller GetSellerByUser (User user);
    public void AddSeller (Seller seller);
}