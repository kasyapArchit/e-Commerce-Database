package beans;

public class Complaint {
    String m_subject;
    String m_text;
    Boolean m_status;
    Long m_mobile_no;

    public Complaint (String subject, String text, Boolean status, Long mobile_no)
    {
        this.m_subject = subject; this.m_text = text; this.m_status = status; this.m_mobile_no = mobile_no;
    }
    public String get_subject () {return m_subject;}
    public String get_text () {return m_text;}
    public Boolean get_status () {return m_status;}
    public Long get_mobile_no () {return m_mobile_no;}

    public String toString () {
        return m_subject + "\n" + m_text + "\n" + (m_status?"yes":"no") + "\n" + "ID: " + m_mobile_no;
    }
}