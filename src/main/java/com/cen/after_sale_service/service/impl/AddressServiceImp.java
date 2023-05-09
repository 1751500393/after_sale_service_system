package com.cen.after_sale_service.service.impl;

import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.mapper.AddressMapper;
import com.cen.after_sale_service.service.IAddressService;
import com.cen.after_sale_service.service.IDistrictService;
import com.cen.after_sale_service.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImp implements IAddressService {
    @Autowired(required = false)
    private AddressMapper addressMapper;
    //  在添加用户的收货地址的业务层依赖于IDIstrictService的业务层接口
    @Autowired(required = false)
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
//   调用收货地址统计方法，统计是否是第一条或超过20条
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("用户收货地址超过20");
        }
//        对address对象中的数据进行补全：省市区的相关数据
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityName());
        String areaName = districtService.getNameByCode(address.getAreaName());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);


//   uid、isDelete
        address.setUid(uid);
//        1表示默认、0表示不默认
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
//       补全修改者、修改时间....
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        address.setCreatedTime(new Date());
//        调用插入收货地址
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("插入收货地址产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaName(null);
            address.setAreaCode(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址为空");
        }
//        检测当前获取的收货地址数据的归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问数据");
        }
//    将所有的收货地址
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新数据尝试未知异常");
        }
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据尝试未知异常");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问异常");
        }


        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除数据产生未知异常");
        }
        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            //终止程序
            return;
        }

        if (result.getIsDefault()==1){
            Address address = addressMapper.findLastModified(uid);
            rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        }


        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("收货地址数据不存在的异常");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}
