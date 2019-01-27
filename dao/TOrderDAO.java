package dao;

import beans.TOrder;
import java.util.ArrayList;

public interface TOrderDAO {
    public ArrayList<TOrder> GetOrderByUserId(Long customer_mobile_no);
    public TOrder GetOrderByOrderId(Long order_id);
    public Long GetNewId();
    public void AddTOrder (TOrder torder);
    public void DeleteTOrder (TOrder torder);
    public void UpdateTorder (TOrder upd);
}