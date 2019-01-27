package dao;

import java.util.ArrayList;

import beans.OrderItem;

public interface OrderItemDAO {
    public OrderItem GetProductByOrderItemId (Long order_item_id);
    public ArrayList<OrderItem> GetProductByOrderId (Long order_id);
    public Long GetNewId();
    public void AddOrderItem (OrderItem order_item);
    public void DeleteOrderItem (OrderItem order_item);
    public void UpdateOrderItem (OrderItem upd);
}