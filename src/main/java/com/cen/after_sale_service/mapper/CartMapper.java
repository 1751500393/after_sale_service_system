package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.Cart;
import com.cen.after_sale_service.vo.CartVO;

import java.util.Date;
import java.util.List;

/**
 * 订单车持久层接口
 */
public interface CartMapper {
    /**
     * 插入订单车数据
     *
     * @param cart 购物车数据
     * @return 受影响行数
     */
    Integer insert(Cart cart);

    /**
     * 更新订单车某件商品的数量
     *
     * @param cid          购物车数据id
     * @param num          更新的数量
     * @param modifiedTime 修改时间
     * @param modifiedUser 修改人
     * @return 受影响行数
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据商品的id和订单车车的id查找购物车数据
     *
     * @param pid 商品id
     * @param uid 用户id
     * @return 购物车对象
     */
    Cart findByUidAndPid(Integer uid, Integer pid);

    /**
     *根据用户id查询，多表结果集
     * @param uid 用户id
     * @return 自定义对象集合
     */
    List<CartVO> findVOByUid(Integer uid);
    /**
     * 根据购物车id查询订单车
     * @param cid  订单车
     * @return 订单车对象
     * */
    Cart findByCid(Integer cid);
    /**
     *根据购物车id查询，多表结果集
     * @param cids 购物车id
     * @return 自定义对象集合
     */
    List<CartVO> findVOByCid(Integer[] cids);
}
