package beans;

public class Customer extends User {
    public Customer () {}
    public Customer (
        Long mobile_no,
        String password,
        String fname, String lname,
        String address_line_1,
        String address_line_2,
        String address_line_3)
        {
            super (mobile_no, password, fname, lname, address_line_1, address_line_2, address_line_3);
        }
}