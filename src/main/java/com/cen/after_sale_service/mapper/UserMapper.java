package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户模块的持久层接口
 */
public interface UserMapper {
    /**
     * 插入用户的数据
     *
     * @param user 用户的数据
     * @return 受影响的行数, 更具体来说是rowCount是增加了多少行。(增删改查，都受影响的行数作为返回值，可以根据返回值判断是否执行)
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     *
     * @param username 用户名
     * @return 如果找到对应的用户则返回用户的数据，如果没有找到返回null值
     */

    User findByUsername(String username);

    /**
     * 根据uid来修改用户的密码,修改执行者,修改时间
     *
     * @param uid          用户id
     * @param password     用户密码
     * @param modifiedUser 修改执行者
     * @param modifiedTime 修改的时间
     * @return Integer 受影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据uid来查用户是否存在
     *
     * @param uid 用户id
     * @return User 存在用户则返回User，否则返回null
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据
     *
     * @param user 用户数据
     * @return Integer  返回值为受影响的行数（row）
     */
    Integer updateInfoByUid(User user);

    /**
     *  @Param("SQL映射文件#{}里面的占位符，的变量名")：解决当sql语句的占位符，和映射接口方法参数名不一致时，可以使参数强行注入某个占位符上。
     * 根据uid值修改用户头像
     * @param uid          用户id数据
     * @param avatar       用户头像地址
     * @param modifiedUser 修改执行者
     * @param modifiedTime 修改的时间
     * @return Integer  返回值为受影响的行数（row）
     */
//   举例： @Param("uid") Integer uid,类似数据库结果集，若起名不相同还可以强行匹配
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
