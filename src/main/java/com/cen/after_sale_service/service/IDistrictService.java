package com.cen.after_sale_service.service;

import com.cen.after_sale_service.entity.District;

import java.util.List;

public interface IDistrictService {
/**
 * 根据父代号来查询区域信息(省市区)
 * @param parent 父代码
 * @return 多个区域信息
 * */
 List<District> getByParent(String parent);
/**
 * 根据代号查询城市名
 * @parm  code  城市代码
 * @return 城市名
 * */
String getNameByCode(String code);
}
