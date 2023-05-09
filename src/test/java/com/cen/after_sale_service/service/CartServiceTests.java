package com.cen.after_sale_service.service;

import com.cen.after_sale_service.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
//标注当前类是测试类，不会随项目一块打包

//@RunWith表示启动这个单元测试类(单元测试类不能运行)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
    //依赖接口的语法,userMapper的报错问题。原因:接口是不能直接创建
    @Autowired(required = false)
    private ICartService cartService;

    @Test
    public void addToCart() {
//            cartService.addToCart(22,10000002,2,"牛马");
        cartService.addToCart(18,10000001,10,"牛马");
    }
    @Test
    public void addNun(){
    Integer rows=    cartService.addNum(1,18,"牛皮");
        System.out.println(rows);
    }

}


