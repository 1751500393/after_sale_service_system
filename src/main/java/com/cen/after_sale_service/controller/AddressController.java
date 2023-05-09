package com.cen.after_sale_service.controller;

import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.service.IAddressService;
import com.cen.after_sale_service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSesssion(session);
        addressService.addNewAddress(uid, username, address);
        return new JsonResult<>(OK);
    }

    //@RequestMapping访问路径,不需要,json语句就传到此路径,json语句的路径主要用于ajax的获取
    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK, data);
    }

    //  RestFul风格
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.setDefault(aid,
                getuidFromSession(session),
                getUsernameFromSesssion(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,HttpSession session){
        addressService.delete(aid,getuidFromSession(session),getUsernameFromSesssion(session));
        return new JsonResult<>(OK);
    }
}
