package utils;

public class OItem {
    private Long product_id;
    private int quantity;

    public OItem (Long pid, int q) {
        product_id = pid;
        quantity = q;
    }

    public Long GetProductID () {return product_id;}
    public int GetQuantity () {return quantity;}
}