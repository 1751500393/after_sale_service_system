package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired(required = false)
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
      List<District>  districtList=districtMapper.findByParent("210100");
        for (District district:districtList){
            System.out.println(district);
        }
    }
    @Test
    public void findNameByCode(){
        String name=districtMapper.findNameByCode("610000");
        System.out.println(name);
    }

}
