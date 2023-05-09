package com.cen.after_sale_service.controller;

import com.cen.after_sale_service.entity.Order;
import com.cen.after_sale_service.service.IOrderService;
import com.cen.after_sale_service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("orders")
public class OrderContraller extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Order data = orderService.create(
                aid,
                cids,
                getuidFromSession(session),
                getUsernameFromSesssion(session));
        return new JsonResult<>(OK, data);
    }
}
