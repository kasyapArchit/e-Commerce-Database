package utils;

public class Pair {
    private Object m_first, m_second;

    public Pair () {}
    public Pair (Object first, Object second)
    {
        m_first = first; m_second = second;
    }

    public Object get_first () {return m_first;}
    public Object get_second () {return m_second;}
}