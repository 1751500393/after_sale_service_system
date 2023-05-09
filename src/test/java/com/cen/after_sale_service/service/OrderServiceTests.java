package com.cen.after_sale_service.service;

import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.entity.Order;
import com.cen.after_sale_service.service.impl.OrderServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//标注当前类是测试类，不会随项目一块打包

//@RunWith表示启动这个单元测试类(单元测试类不能运行)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
    //依赖接口的语法,userMapper的报错问题。原因:接口是不能直接创建
    @Autowired(required = false)
    private OrderServiceImp orderServiceImp;
    @Test
    public void create(){
        Integer[] cids = {5,4,6};
        Order order = orderServiceImp.create(10, cids, 19, "admin");
        System.out.println(order);
    }

}


