package com.cen.after_sale_service.mapper;

import com.cen.after_sale_service.entity.Product;

import java.util.List;

/**处理商品数据的持久层接口*/
public interface ProductMapper {
/**
 * 查询热销商品的前四名
 * @return 热销商品前四名的集合
 * */
List<Product> findHotList();
/**
 * 根据id查询查询产品
 * @param id 用户id
 * @return  Product 返回产品页面
 * */
Product findById(Integer id);
}
