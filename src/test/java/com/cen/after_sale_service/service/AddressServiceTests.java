package com.cen.after_sale_service.service;

import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//标注当前类是测试类，不会随项目一块打包

//@RunWith表示启动这个单元测试类(单元测试类不能运行)，需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests {
    //依赖接口的语法,userMapper的报错问题。原因:接口是不能直接创建
    @Autowired(required = false)
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("23121313");
        address.setName("驴友");
        addressService.addNewAddress(19, "管理员", address);
    }

    @Test
    public void setDefault() {
        addressService.setDefault(2, 19, "牛有德");
    }

    @Test
    public void  delete(){
      addressService.delete(2,19,"管理员");
    }
    @Test
    public void getByAid(){
        Address address=addressService.getByAid(10,19);
        System.out.println(address);
    }
}


