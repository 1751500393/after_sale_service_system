package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.entity.Order;
import com.cen.after_sale_service.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTests {
    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Test
    public void insertOrder(){
        Order order=new Order();
        order.setUid(19);
        order.setRecvName("困困");
        order.setRecvPhone("12213123");
        Integer rows=orderMapper.insertOneOrder(order);
        System.out.println(rows);
    }
    @Test
    public void insertOrderItem(){
        OrderItem orderItem=new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000001);
        orderItem.setTitle("牛皮纸记事本");
        Integer rows=orderMapper.insertOneOrderItem(orderItem);
        System.out.println(rows);
    }



}
