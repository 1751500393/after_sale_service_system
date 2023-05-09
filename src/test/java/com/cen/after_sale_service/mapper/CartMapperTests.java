package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.entity.Cart;
import com.cen.after_sale_service.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired(required = false)
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(18);
        cart.setPid(10000001);
        cart.setNum(2);
        cart.setPrice(1000L);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 4, "李四", new Date());
    }

    @Test
    public void findByUidAndPid() {
       Cart cart= cartMapper.findByUidAndPid(18,10000001);
        System.out.println(cart);

    }
    @Test
    public void findVOByUid(){
        List<CartVO> cartVOList=cartMapper.findVOByUid(22);
        System.out.println(cartVOList);
    }
    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(1));
    }
    @Test
    public void  findVOByCid(){
        Integer[] cids={1,2,3,4};
        System.out.println(cartMapper.findVOByCid(cids));
    }
}
