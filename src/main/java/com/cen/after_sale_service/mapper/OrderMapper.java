package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.Order;
import com.cen.after_sale_service.entity.OrderItem;

public interface OrderMapper{
    /**
     * 插入一条order订单数据
     *
     * @param order 订单对象
     * @return 返回受影响行数
     */
    int insertOneOrder(Order order);
    /**向order_item表中插入一条orderItem数据
     * @param orderItem 订单项目
     * @return 受到影响的行数
     */
    int insertOneOrderItem(OrderItem orderItem);

}
