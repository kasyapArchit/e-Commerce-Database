package beans;

public class Seller extends User {
    private Boolean m_approved = false;
    private Long m_gst_no;

    public Seller () {}
    public Seller (
        Long mobile_no,
        String password,
        String fname, String lname,
        String address_line_1,
        String address_line_2,
        String address_line_3,

        Boolean approved, Long gst_no) {
            super (mobile_no, password, fname, lname, address_line_1, address_line_2, address_line_3);
            this.m_approved = approved;
            this.m_gst_no = gst_no;
    }

    public Long get_gst_no () {
        return this.m_gst_no;
    }
    public void set_gst_no (Long gst_no) {
        this.m_gst_no = gst_no;
    }

    public Boolean get_approved () {
        return this.m_approved;
    }
    public void set_approved (Boolean approved)
    {
        this.m_approved = approved;
    }
}