package com.cen.after_sale_service.service.impl;

import com.cen.after_sale_service.entity.Product;
import com.cen.after_sale_service.mapper.ProductMapper;
import com.cen.after_sale_service.service.IProductService;
import com.cen.after_sale_service.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImp implements IProductService {
    @Autowired(required = false)
    private ProductMapper productMapper;
    @Override
    public List<Product> findHotList() {
        List<Product> list=productMapper.findHotList();
        for (Product product:list){
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedTime(null);
            product.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
       Product product=productMapper.findById(id);
       if (product==null){
           throw  new ProductNotFoundException("商品未找到");
       }
       product.setPriority(null);
       product.setCreatedUser(null);
       product.setCreatedTime(null);
       product.setModifiedUser(null);
       product.setModifiedTime(null);
       return product;
    }
}
