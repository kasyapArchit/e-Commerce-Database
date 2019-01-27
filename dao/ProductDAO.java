package dao;

import beans.Product;

import java.util.ArrayList;

public interface ProductDAO {
    public Product GetProductByProductId (Long product_id);
    public Product GetProductByProductIdAndSeller (Long product_id, Long seller_mobile_no);
    public void AddProduct (Product product);
    public void DeleteProduct (Product product);
    public void UpdateProduct (Product upd);
    public ArrayList<Product> GetAll ();
    public ArrayList <Product> GetProductBySeller (Long seller_mobile_no);
}