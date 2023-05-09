package com.cen.after_sale_service.service.impl;

import com.cen.after_sale_service.entity.User;
import com.cen.after_sale_service.mapper.UserMapper;
import com.cen.after_sale_service.service.IUserService;
import com.cen.after_sale_service.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 用户模块业务层实现类
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用findByUserName(username)判断用户是否被注册
        User result = userMapper.findByUsername(username);
        if (result != null) {
            throw new UsernameDuplicatedException("用户名被占用");
        }
        //加密md5
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(oldPassword, salt);
//        将加密后的密码重新补全设置到user对象中
//        需要记录盐值
        user.setSalt(salt);
        user.setPassword(md5Password);
        //补全数据:is_delete设置0
        user.setIsDelete(0);
        //补全数据：4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User Login(String username, String password) {
//根据用户名称来查询用户数据是否存在，如果不在则抛出异常
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户名不存在");
        }
//  检测用户密码是否匹配
//      1.先获取数据库加密之后的密码
//      2.和用户的传递过来的密码进行比较
//      2.1.先获取盐值
        String salt = result.getSalt();
//      2.2，用盐值加密用户输入的密码
        String newMd5Password = getMD5Password(password, salt);
//     3、比较密码
        if (!newMd5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("密码与用户名不匹配");
        }
//       4、判断is_delete字段的值是否为1表示标记为删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
//       减少user对象的内容，提高后端传输效率,提升系统性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
//        返回user对象，返回为了辅助其他页面做数据展示
        return user;

    }

    // 实现服务层修改密码方法
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
//            比较原始密码和数据库中密码进行比较
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
//            将新的密码设置到数据库中,将新密码进行加密再进行更新
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
//        返回结果集,代表数据库新增一行
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
    }
//用于获取用户数据，显示在前端，方便用户修改数据
    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
//        减少User内容，保证数据安全性和运行效率(限制用户能修改的数据)
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }
//user对象中的数据phone\email\gender(因为方法以上述获取),手动在
    @Override
    public void changeInfo(Integer uid, String username, User user) {
       User result= userMapper.findByUid(uid);
       if (result==null||result.getIsDelete()==1){
                throw  new UserNotFoundException("用户不存在");

       }
       user.setUid(uid);
       user.setModifiedUser(username);
       user.setModifiedTime(new Date());
       Integer rows=userMapper.updateInfoByUid(user);
        if (rows!=1){
            throw new UpdateException("更新时产生异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        //        查询当前的用户数据是否存在
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        Integer rows=userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if (rows!=1){
            throw new UpdateException("更新头像产生未知异常");
        }
    }


    //加密处理MD5
    private String getMD5Password(String password, String salt) {
//        md5加密算法方法调用(加密三次)
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
