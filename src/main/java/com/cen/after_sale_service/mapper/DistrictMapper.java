package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.District;

import java.util.List;

/**
 * 省市区数据持久层接口
 */
public interface DistrictMapper {
    /**
     * 根据地址的父代号查询
     *
     * @param parent 父代号
     * @return List<District> 某个父区域下的所有区域列表
     */
    List<District> findByParent(String parent);

    /**
     * 根据code码查询城市名
     *
     * @param code 城市代码
     * @return String  城市名
     */
    String findNameByCode(String code);
}
