package com.cen.after_sale_service.controller;

import com.cen.after_sale_service.controller.ex.*;
import com.cen.after_sale_service.entity.Address;
import com.cen.after_sale_service.service.ex.*;
import com.cen.after_sale_service.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层类的基类
 */
public class BaseController {
    //  正常状态码设200（自定义）,操作成功的状态码
    public static final int OK = 200;

    //   请求处理方法，此方法的返回值就是要传递给前端的数据
//    被此注解的方法，会自动将异常对象传递到此方法的参数列表中
//    当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就充当了请求处理方法,方法的返回值直接给到前端
//   此注解用于统一处理抛出的异常
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
//   异常报出都是JsonResul类，在工具包类中，所以返回值设置为JsonResult
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessge("用户名已经被占用");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
            result.setMessge("用户数据不存在的异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
            result.setMessge("用户名的密码错误的异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4003);
            result.setMessge("用户名的地址超出上限异常");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4004);
            result.setMessge("用户收货地址不存在");
        } else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMessge("收货地址数据非法访问的异常");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessge("货物查找为空的的异常");
            } else if (e instanceof CartNotFoundException) {
                result.setState(4007);
                result.setMessge("订单车数据不存在的异常");
            } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessge("注册时产生未知的异常");
        } else if (e instanceof UpdateException) {
            result.setState(5001);
            result.setMessge("更新数据时产生未知的异常");
        } else if (e instanceof DeleteException) {
            result.setState(5002);
            result.setMessge("删除数据时产生未知的异常");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessge("文件空异常");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessge("文件尺寸异常");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessge("文件类型异常");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessge("文件状态异常");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessge("文件上传IO流异常");
        }
        return result;


    }
//封装session

    /**
     * 获取session对象
     *
     * @param session
     * @return Integer(当前用户uid的值)
     * 在实现类中重写了父类的toString(),
     */
    public final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * @param session
     * @return String(当前用户的名字)
     */
    public final String getUsernameFromSesssion(HttpSession session) {
        return session.getAttribute("username").toString();

    }
}
