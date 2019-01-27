package beans;

public class Product {
    protected float m_offer=0;
    protected int m_available_quantity;
    protected Long m_product_id, m_seller_mobile_no;
    protected String m_product_name;
    protected float m_price;

    public Product () {}
    public Product (float offer,
        int available_quantity,
        Long product_id,
        Long seller_mobile_no,
        String product_name,
        float price
    ) {
        this.m_offer = offer; this.m_available_quantity = available_quantity;
        this.m_product_id = product_id; this.m_seller_mobile_no = seller_mobile_no;
        this.m_product_name = product_name; this.m_price = price;
    }

    public float get_offer () {return this.m_offer;}
    public int get_available_quantity () {return this.m_available_quantity;}
    public Long get_product_id () {return this.m_product_id;}
    public Long get_seller_mobile_no () {return this.m_seller_mobile_no;}
    public String get_product_name () {return this.m_product_name;}
    public float get_price () {return this.m_price;}

    public void set_offer (float offer) {this.m_offer = offer;}
    public void set_available_quantity (int available_quantity)
    {
        this.m_available_quantity = available_quantity;
    }
    public void set_product_id (Long product_id)
    {
        this.m_product_id = product_id;
    }
    public void set_seller_mobile_no (Long seller_mobile_no)
    {
        this.m_seller_mobile_no = seller_mobile_no;
    }
    public void set_product_name (String product_name)
    {
        this.m_product_name = product_name;
    }
    public void set_price(float price) {this.m_price = price;}

}