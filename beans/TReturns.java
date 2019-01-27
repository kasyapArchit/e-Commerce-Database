package beans;


public class TReturns {
    protected Long m_order_item_id;
    protected float m_amount;
    protected int m_stat;

    public TReturns () {}
    public TReturns (Long order_item_id, float amount, int stat)
    {
        this.m_order_item_id = order_item_id; this.m_amount = amount;
        this.m_stat = stat;
    }

    public Long get_order_item_id(){return this.m_order_item_id;}
    public float get_amount(){return this.m_amount;}
    public int get_stat(){return this.m_stat;}

    public void set_order_item_id(Long x){this.m_order_item_id=x;}
    public void set_amount(float x){this.m_amount=x;}
    public void set_stat(int x){this.m_stat=x;}
}