package view;

import cresponse.Cresponse;

public interface View {
    public void Display ();
    public Cresponse GetResponse ();
    public View GetNextView ();
}