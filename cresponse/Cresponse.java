package cresponse;

public class Cresponse {
    private Object m_cresponse;

    public Cresponse (Object cr) 
    {
        this.m_cresponse = cr;
    }
    public Cresponse () {}

    public Object GetObject () {
        return this.m_cresponse;
    }
}