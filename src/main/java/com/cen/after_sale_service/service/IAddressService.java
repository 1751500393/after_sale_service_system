package com.cen.after_sale_service.service;

import com.cen.after_sale_service.entity.Address;

import java.util.List;

/**
 * 收货地址业务层接口
 */
public interface IAddressService {
    /**
     * 新增收货地址
     *
     * @param uid      用户id
     * @param username 用户名字
     * @parm address 用户地址信息(另外处理address)
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 根据id查询用户地址集合
     *
     * @param uid 用户id
     * @parm address 用户地址信息(另外处理address)
     */
    List<Address> getByUid(Integer uid);

    /**
     * 修改某个用户的某条收货地址数据为默认收货地址
     *
     * @param aid      收货地址的id
     * @param uid      用户的id
     * @param username 表示修改的人
     */
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 删除用户选中的收货地址数据
     *
     * @param aid      收货地址id
     * @param uid      用户id
     * @param username 用户名(修改人)
     */
    void delete(Integer aid, Integer uid, String username);

    /**
     *根据用户的id和地址的id查询地址，用于订单的创建
     *
     * @param aid  地址id
     * @param uid  用户id
     * @return 地址对象
     */
    Address getByAid(Integer aid, Integer uid);

}
