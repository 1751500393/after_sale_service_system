package com.cen.after_sale_service.controller;

import com.cen.after_sale_service.entity.Product;
import com.cen.after_sale_service.service.IProductService;
import com.cen.after_sale_service.service.impl.ProductServiceImp;
import com.cen.after_sale_service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    private ProductServiceImp iProductService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {
        List<Product> data = iProductService.findHotList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        Product data = iProductService.findById(id);
        return new JsonResult<Product>(OK, data);
    }
}
