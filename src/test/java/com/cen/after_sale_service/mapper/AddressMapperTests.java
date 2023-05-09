package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.entity.User;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired(required = false)
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(19);
        address.setPhone("1232193123");
        address.setName("男朋友");
        addressMapper.insert(address);
    }
    @Test
    public void countByUid(){
        Integer count=addressMapper.countByUid(19);
        System.out.println(count);
    }
    @Test
    public void findByUid(){
      List<Address> list= addressMapper.findByUid(19);
        System.out.println(list);
    }
    @Test
   public void findByAid(){
        Address address=addressMapper.findByAid(1);
        System.out.println(address);
   }
    @Test
    public void updateNonDefault(){
        Integer row=addressMapper.updateNonDefault(19);
        System.out.println(row);
    }
    @Test
    public void updateDefaultByAid(){
            Integer row =addressMapper.updateDefaultByAid(1,"绝望的狗",new Date());
        System.out.println(row);
    }
    @Test
    public void deleteByAid() {
      addressMapper.deleteByAid(1);
    }

    @Test
    public void findLastModified() {
     Address address=   addressMapper.findLastModified(19);
        System.out.println(address);
    }
}
