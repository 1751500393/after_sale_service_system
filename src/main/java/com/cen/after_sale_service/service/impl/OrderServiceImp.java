package com.cen.after_sale_service.service.impl;

import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.entity.Order;
import com.cen.after_sale_service.entity.OrderItem;
import com.cen.after_sale_service.mapper.AddressMapper;
import com.cen.after_sale_service.mapper.OrderMapper;
import com.cen.after_sale_service.service.*;
import com.cen.after_sale_service.service.ex.*;
import com.cen.after_sale_service.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImp implements IOrderService {
    @Autowired(required = false)
    private OrderMapper orderMapper;

    //需要调用业务层的getByAid方法
    @Autowired(required = false)
    private IAddressService addressService;

    //需要调用业务层的getVOByCids方法
    @Autowired(required = false)
    private ICartService cartService;
    @Autowired(required = false)
    //需要调用业务层的getByUid方法
    private IUserService userService;

    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {

        //返回的列表中的对象都是即将下单的
        List<CartVO> list = cartService.getVOByCid(uid, cids);

        long totalPrice = 0L;
        for (CartVO cartVO : list) {
            totalPrice += cartVO.getRealPrice() * cartVO.getNum();

        }
        Address address = addressService.getByAid(aid, uid);
        Order order = new Order();
        order.setUid(uid);

        //封装收货地址
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());

        //封装创建时间,支付状态和总价
        order.setOrderTime(new Date());
        order.setStatus(0);
        order.setTotalPrice(totalPrice);

        //封装四个日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        Integer rows = orderMapper.insertOneOrder(order);
        if (rows != 1) {
            throw new InsertException("插入数据时产生未知的异常");
        }

        //插入数据——将某条订单的所有商品的详细数据插入
        for (CartVO cartVO : list) {
            OrderItem orderItem = new OrderItem();

            /**
             * 此时获取的oid不为空,因为在配置文件里面开启了oid主
             * 键自增,所以上面的代码执行插入时就自动将oid赋值了
             */
            orderItem.setOid(order.getOid());

            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());

            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());

            rows = orderMapper.insertOneOrderItem(orderItem);
            if (rows != 1) {
                throw new InsertException("插入数据时产生未知的异常");
            }
        }
        return order;
    }
}
