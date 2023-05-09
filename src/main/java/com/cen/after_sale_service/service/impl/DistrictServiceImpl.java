package com.cen.after_sale_service.service.impl;

import com.cen.after_sale_service.entity.District;
import com.cen.after_sale_service.mapper.DistrictMapper;
import com.cen.after_sale_service.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImpl implements IDistrictService {
  @Autowired(required = false)
  private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
       List<District>  districtList=districtMapper.findByParent(parent);
//      进行传输时候，可以将无效数据设置为空,节省流量，提升效率
        for (District d:districtList){
            d.setId(null);
            d.setParent(null);
        }
       return districtList;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
