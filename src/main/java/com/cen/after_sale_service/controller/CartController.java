package com.cen.after_sale_service.controller;

import com.cen.after_sale_service.entity.Cart;
import com.cen.after_sale_service.service.ICartService;
import com.cen.after_sale_service.util.JsonResult;
import com.cen.after_sale_service.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("carts")
@RestController
public class CartController extends BaseController{

    @Autowired(required =false)
    private ICartService cartService;
    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session){
        cartService.addToCart(getuidFromSession(session),pid,amount,getUsernameFromSesssion(session));
        return  new JsonResult<>(OK);
    }
    @RequestMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
      List<CartVO> data=  cartService.findVOByUid(getuidFromSession(session));
        return  new JsonResult<>(OK,data);
    }
    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session){
      Integer data=  cartService.addNum(cid,getuidFromSession(session),getUsernameFromSesssion(session));
        return  new JsonResult<>(OK,data);
    }
    @RequestMapping("{cid}/num/reduce")
    public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session){
        Integer data=  cartService.reduceNum(cid,getuidFromSession(session),getUsernameFromSesssion(session));
        return  new JsonResult<>(OK,data);
    }
    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids,HttpSession session){
        List<CartVO> data=  cartService.getVOByCid(getuidFromSession(session),cids);
        return  new JsonResult<>(OK,data);
    }
}
