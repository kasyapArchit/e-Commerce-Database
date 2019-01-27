package beans;


public class TOrder {
    protected Long m_order_id;
    protected Long m_customer_mobile_no;
    protected float m_total_amount;
    protected String m_delivery_address;

    public TOrder () {}
    public TOrder (Long order_id, 
        Long customer_mobile_no,
        float total_amount,
        String delivery_address
    ) {
        this.m_order_id = order_id; this.m_customer_mobile_no = customer_mobile_no;
        this.m_delivery_address = delivery_address;
        this.m_total_amount = total_amount;
    }

    public Long get_order_id() {return this.m_order_id;}
    public Long get_customer_mobile_no() {return this.m_customer_mobile_no;}
    public float get_total_amount() {return this.m_total_amount;}
    public String get_delivery_address() {return this.m_delivery_address;}

    public void set_order_id(Long x) {this.m_order_id=x;}
    public void set_customer_mobile_no(Long x) {this.m_customer_mobile_no=x;}
    public void set_total_amount(float x) {this.m_total_amount=x;}
    public void set_delivery_address(String x) {this.m_delivery_address=x;}

}