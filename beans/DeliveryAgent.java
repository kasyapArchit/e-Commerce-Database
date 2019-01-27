package beans;


public class DeliveryAgent {
    protected Long m_mobile_no;
    protected String m_fname, m_lname;

    public DeliveryAgent () {}
    public DeliveryAgent (Long mobile_no, String fname, String lname)
    {
        this.m_mobile_no = mobile_no;
        this.m_fname = fname; this.m_lname = lname;
    }

    public Long get_mobile_no(){return this.m_mobile_no;}
    public String get_fname(){return this.m_fname;}
    public String get_lname(){return this.m_lname;}

    public void set_mobile_no(Long x){this.m_mobile_no=x;}
    public void set_fname(String x){this.m_fname=x;}
    public void set_lname(String x){this.m_lname=x;}
}