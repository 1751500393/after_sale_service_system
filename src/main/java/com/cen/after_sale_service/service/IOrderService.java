package com.cen.after_sale_service.service;

import com.cen.after_sale_service.entity.Order;

public interface IOrderService {
    /**
     * 根据地址id  订单id数组    用户id   创建者名字查找订单
     *
     * @param aid      地址id
     * @param cids     订单id数组
     * @param uid      用户id
     * @param username 创建者名
     * @return 订单对象
     */
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
