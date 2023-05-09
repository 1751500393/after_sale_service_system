package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.User;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
//标注当前类是测试类，不会随项目一块打包

//@RunWith表示启动这个单元测试类(单元测试类不能运行)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
    //依赖接口的语法,userMapper的报错问题。原因:接口是不能直接传
    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 单元测试方法的语法规定(单独运行)
     * 1、必须被@Test注册
     * 2、返回值必须是Void
     * 3、方法的参数列表不指定任何类型
     * 4、方法的访问修饰符必须是pubblic
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("六六");
        user.setPassword("123456");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("张三");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid() {
               userMapper.updatePasswordByUid(16,"321","管理员",new Date());

    }

    @Test
    public void findByUid() {
        System.out.println(userMapper.findByUid(16));
    }
    @Test
    public  void updateInfoByUid(){
        User user=new User();
        user.setUid(21);
        user.setPhone("123456789101123");
        user.setEmail("213131@qq.com");
        user.setGender(1);
      Integer row=userMapper.updateInfoByUid(user);
        System.out.println(row);
    }
    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(22,
                "/upload/avatar.png",
                "管理员",
                new Date());
    }

}
