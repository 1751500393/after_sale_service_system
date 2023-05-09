package com.cen.after_sale_service.controller;
import com.cen.after_sale_service.entity.District;
import com.cen.after_sale_service.service.IDistrictService;
import com.cen.after_sale_service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("districts")
@RestController
public class DistinctController extends BaseController {
  @Autowired(required = false)
    private IDistrictService iDistrictService;
//  distracts开头的请求都拦截到rent()方法中
    @RequestMapping({"/",""})
    public JsonResult<List<District>>  getByParent(String parent){
        List<District> data=iDistrictService.getByParent(parent);
        return  new JsonResult<>(OK,data);
    }
}
