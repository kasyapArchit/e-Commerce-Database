package beans;

public class OrderItem {
    protected Long m_order_id, m_product_id, m_order_item_id;
    protected float m_amount;
    protected int m_quantity, m_stat;

    public OrderItem () {}
    public OrderItem (Long order_item_id, 
        Long order_id, 
        Long product_id,
        float amount,
        int quantity,
        int stat
    ){
        this.m_order_item_id = order_item_id; this.m_order_id = order_id; 
        this.m_product_id = product_id; this.m_amount = amount;
        this.m_quantity = quantity; this.m_stat =stat;
    }

    public Long get_order_item_id(){return this.m_order_item_id;}
    public Long get_order_id(){return this.m_order_id;}
    public Long get_product_id(){return this.m_product_id;}
    public float get_amount(){return this.m_amount;}
    public int get_quantity(){return this.m_quantity;}
    public int get_stat(){return this.m_stat;}
    
    public void set_order_item_id(Long x){this.m_order_item_id=x;}
    public void set_order_id(Long x){this.m_order_id=x;}
    public void set_product_id(Long x){this.m_product_id=x;}
    public void set_amount(float x){this.m_amount=x;}
    public void set_quantity(int x){this.m_quantity=x;}
    public void set_stat(int x){this.m_stat=x;}
    
}