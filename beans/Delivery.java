package beans;


public class Delivery {
    protected Long m_delivery_id, m_mobile_no, m_order_item_id;
    protected int m_stat;

    public Delivery () {}
    public Delivery (Long delivery_id, Long mobile_no, Long order_item_id, int stat)
    {
        this.m_delivery_id = delivery_id;
        this.m_mobile_no = mobile_no;
        this.m_order_item_id = order_item_id;
        this.m_stat = stat;
    }

    public Long get_delivery_id(){return this.m_delivery_id;}
    public Long get_mobile_no(){return this.m_mobile_no;}
    public Long get_order_item_id(){return this.m_order_item_id;}
    public int get_stat(){return this.m_stat;}
    
    public void set_delivery_id(Long x){this.m_delivery_id=x;}
    public void set_mobile_no(Long x){this.m_mobile_no=x;}
    public void set_order_item_id(Long x){this.m_order_item_id=x;}
    public void set_stat(int x){this.m_stat=x;}
}