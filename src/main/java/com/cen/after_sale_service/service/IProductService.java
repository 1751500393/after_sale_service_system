package com.cen.after_sale_service.service;

import com.cen.after_sale_service.entity.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询热门商品
     *
     * @return List<Product>  商品列表
     */
    List<Product> findHotList();

    /**
     *根据商品id查询商品详细
     * @param  id 商品id
     * @return  匹配的商品详细，观察商品的是否匹配
     */
    Product findById(Integer id);
}
