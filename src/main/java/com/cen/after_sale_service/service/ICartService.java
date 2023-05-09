package com.cen.after_sale_service.service;

import com.cen.after_sale_service.vo.CartVO;

import java.util.List;

public interface ICartService {
    /**
     * 将物品添加到订单车
     *
     * @param uid      用户id
     * @param pid      商品id
     * @param amount   新增数量
     * @param username 修改者名称
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    /**
     * 根据用户id查询，多表结果集
     *
     * @param uid 用户id
     * @return 自定义对象
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 更新用户的购物车数据的数量
     *
     * @param cid
     * @param uid
     * @param username
     * @return 增加成功后的数量
     */
    Integer addNum(Integer cid, Integer uid, String username);

    /**
     * 减少用户的购物车数据的数量
     *
     * @param cid
     * @param uid
     * @param username
     * @return 减少成功后的数量
     */
    Integer reduceNum(Integer cid, Integer uid, String username);


    /**
     * 根据用户id和cids数组查找购物车集合
     *
     * @param uid  用户id
     * @param cids 购物车id数组
     * @return 购物车集合
     */
    List<CartVO> getVOByCid(Integer uid, Integer[] cids);
}
