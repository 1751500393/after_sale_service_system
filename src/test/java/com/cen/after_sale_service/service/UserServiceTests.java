package com.cen.after_sale_service.service;

import com.cen.after_sale_service.entity.User;
import com.cen.after_sale_service.mapper.UserMapper;
import com.cen.after_sale_service.service.ex.UpdateException;
import com.cen.after_sale_service.service.ex.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
//标注当前类是测试类，不会随项目一块打包

//@RunWith表示启动这个单元测试类(单元测试类不能运行)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    //依赖接口的语法,userMapper的报错问题。原因:接口是不能直接chuang
    @Autowired(required = false)
    private IUserService userService;

    /**
     * 单元测试方法的语法规定(单独运行)
     * 1、必须被@Test注册
     * 2、返回值必须是Void
     * 3、方法的参数列表不指定任何类型
     * 4、方法的访问修饰符必须是pubblic
     */
    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("kkkkk");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void login() {
        User user = userService.Login("kkkkk", "123456");
        System.out.println(user);
    }

    @Test
    public void changePassword() {
        userService.changePassword(18, "kkkkkk", "123456", "12345678");
    }

    //用于获取用户数据，显示在前端，方便用户修改数据
    @Test
    public void getByUid() {
        System.out.println(userService.getByUid(21));
    }

    //user对象中的数据phone\email\gender(因为方法以上述获取),手动在
    @Test
    public void changeInfo() {
        User user = new User();
        user.setPhone("1231565456");
        user.setEmail("yuan@qq.com");
        user.setGender(0);
        userService.changeInfo(21, "管理员", user);
    }
//    测试修改头像
    @Test
    public void changAvatar(){
        userService.changeAvatar(22,"/upload/test.png","小明");
    }
}


