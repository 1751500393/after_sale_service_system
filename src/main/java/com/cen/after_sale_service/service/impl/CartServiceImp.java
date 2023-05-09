package com.cen.after_sale_service.service.impl;

import com.cen.after_sale_service.entity.Cart;
import com.cen.after_sale_service.entity.Product;
import com.cen.after_sale_service.mapper.CartMapper;
import com.cen.after_sale_service.mapper.ProductMapper;
import com.cen.after_sale_service.service.ICartService;
import com.cen.after_sale_service.service.ex.AccessDeniedException;
import com.cen.after_sale_service.service.ex.CartNotFoundException;
import com.cen.after_sale_service.service.ex.InsertException;
import com.cen.after_sale_service.service.ex.UpdateException;
import com.cen.after_sale_service.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImp implements ICartService {
    /**
     * 订单车业务层依赖于订单车持久层和商品层的持久层
     */
    @Autowired(required = false)
    private CartMapper cartMapper;
    @Autowired(required = false)
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        //查询当前要添加的订单车是否在表中以存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date=new Date();
        if (result == null) {
//           订单车中不存在此物品,进行新增
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
//            价格设置
            Product product=productMapper.findById(pid);
            cart.setPrice(product.getPrice());
//            日志四项
            cart.setModifiedTime(date);
            cart.setModifiedUser(username);
            cart.setCreatedTime(date);
            cart.setCreatedUser(username);
           Integer rows=cartMapper.insert(cart);
           if (rows!=1){
               throw new InsertException("插入数据时产生未知异常");
           }
        } else {
//            订单车不存在此物品,进行添加
            Integer num=result.getNum()+amount;
           Integer rows= cartMapper.updateNumByCid(result.getCid(),num,username,date);
           if (rows!=1){
               throw new UpdateException("更新产生未知异常");
           }
        }
    }

    @Override
    public List<CartVO> findVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

        @Override
        public Integer addNum(Integer cid, Integer uid, String username) {
            Cart result=cartMapper.findByCid(cid);
            if (result==null){
                throw new CartNotFoundException("数据不存在");
            }
            if (!result.getUid().equals(uid)){
                throw new AccessDeniedException("非法访问数据");
            }
            Integer num=result.getNum()+1;
           Integer rows= cartMapper.updateNumByCid(cid,num,username,new Date());
           if (rows!=1){
               throw  new UpdateException("更新数据失败");
           }
           return rows;
        }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart result=cartMapper.findByCid(cid);
        if (result==null){
            throw new CartNotFoundException("数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问数据");
        }
        Integer num=result.getNum()-1;
        Integer rows= cartMapper.updateNumByCid(cid,num,username,new Date());
        if (rows!=1){
            throw  new UpdateException("更新数据失败");
        }
        return rows;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
       List<CartVO> list=cartMapper.findVOByCid(cids);
        Iterator<CartVO> it=list.iterator();
        while (it.hasNext()){
           CartVO cartVO= it.next();
           if (!cartVO.getUid().equals(uid)){
               list.remove(cartVO);
           }
        }
        return list;
    }
}
