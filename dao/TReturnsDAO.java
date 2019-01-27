package dao;

import java.util.ArrayList;

import beans.TReturns;;

public interface TReturnsDAO{
    public TReturns GetTReturnsByOrderItemId (Long order_item_id);
    public ArrayList<TReturns> GetAllTReturns ();
    public void AddTReturns (TReturns tReturns);
    public void DeleteTReturns (TReturns tReturns);
    public void UpdateTReturns (TReturns upd);
}