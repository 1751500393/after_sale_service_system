package com.cen.after_sale_service.service;

import com.cen.after_sale_service.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户模块业务层接口,接口都是用于调用的
 */
public interface IUserService {
    /**
     * 用户注册方法
     *
     * @param user 用户对象
     * @return void
     */
    void reg(User user);

    /**
     * 用户登录功能
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数据，如果没有则返回null值
     */
    User Login(String username, String password);

    /**
     * 用户修改密码
     *
     * @param uid         用户id
     * @param username    用户名字
     * @param oldPassword 用户旧密码
     * @param newPassword 用户新密码
     */
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 根据用户id查询用户的数据
     *
     * @param uid 用户id
     * @return 用户的数据
     */
    User getByUid(Integer uid);

    /**
     * 更新用户的数据
     *
     * @param uid      用户的id
     * @param username 用户的名字
     * @param user     用户对象
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 修改用户头像
     *
     * @param uid      用户的id
     * @param avatar   用户的头像地址
     * @param username 用户名字
     */
    void changeAvatar(Integer uid, String avatar, String username);
}
