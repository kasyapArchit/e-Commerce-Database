package beans;

public class User {
    
    protected Long m_mobile_no;
    protected String m_password;
    protected String m_fname;
    protected String m_lname;
    protected String m_address_line_1;
    protected String m_address_line_2;
    protected String m_address_line_3;

    public User () {}

    public User (
        Long mobile_no,
        String password,
        String fname, String lname,
        String address_line_1,
        String address_line_2,
        String address_line_3) {
            this.m_mobile_no = mobile_no;
            this.m_fname = fname;this.m_password = password; this.m_lname = lname; this.m_address_line_1 = address_line_1;
            this.m_address_line_2 = address_line_2; this.m_address_line_3 = address_line_3;
    }

    public Long get_mobile_no () {
        return this.m_mobile_no;
    }
    public void set_mobile_no (Long mobile_no) {
        this.m_mobile_no = mobile_no;
    }

    public String get_password() {
        return this.m_password;
    }
    
    public void set_password(String m_password) {
        this.m_password = m_password;
    }
    
    public String get_fname() {
        return this.m_fname;
    }
    
    public void set_fname(String m_fname) {
        this.m_fname = m_fname;
    }

    public String get_lname() {
        return this.m_lname;
    }
    
    public void set_lname(String m_lname) {
        this.m_lname = m_lname;
    }
    
    public String get_address_line_1() {
        return this.m_address_line_1;
    }
    
    public void set_address_line_1(String m_address_line_1) {
        this.m_address_line_1 = m_address_line_1;
    }
    
    public String get_address_line_2() {
        return this.m_address_line_2;
    }
    
    public void set_address_line_2(String m_address_line_2) {
        this.m_address_line_2 = m_address_line_2;
    }
    
    public String get_address_line_3() {
        return this.m_address_line_3;
    }
    
    public void set_address_line_3(String m_address_line_3) {
        this.m_address_line_3 = m_address_line_3;
    }
}