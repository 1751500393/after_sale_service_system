package com.cen.after_sale_service.controller;

import com.cen.after_sale_service.controller.ex.FileEmptyException;
import com.cen.after_sale_service.controller.ex.FileStateException;
import com.cen.after_sale_service.controller.ex.FileTypeException;
import com.cen.after_sale_service.controller.ex.FileUploadIOException;
import com.cen.after_sale_service.entity.User;
import com.cen.after_sale_service.service.IUserService;
import com.cen.after_sale_service.service.ex.InsertException;
import com.cen.after_sale_service.service.ex.UsernameDuplicatedException;
import com.cen.after_sale_service.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//       表示此方法的响应结果以json格式进行数据的响应给到前端,ResponseBody+Controller的合体
@RestController
//网络地址，当成目录名即可
@RequestMapping("users")
//继承BaseContoller,把繁琐的报错统一处理
public class UserController extends BaseController {
    @Autowired(required = false)
    private IUserService userService;

    /**
     * 1.接收数据方式:请求处理方法的参数列表设置为pojo类型来接收前端数据，
     * SpringBoot会将前端url地址中参数名和pojo类的属性进行比较，如果
     * 这两个名称相同，则将注入到pojo类中对应
     */
    //    优化后
    @RequestMapping("reg")
    public JsonResult reg(User user) {
        userService.reg(user);

        return new JsonResult<>(OK);
    }
//    优化前
/**
 //        映射请求（方法）
 //网络地址，当成目录名即可
 @RequestMapping("reg") public JsonResult reg(User user) {
 //    创建响应结果对象,接受错误信息
 JsonResult<Void> result = new JsonResult<>();
 try {
 userService.reg(user);
 result.setState(2000);
 result.setMessge("用户注册成功");
 }
 //可能用户名被占用
 catch (UsernameDuplicatedException e) {
 //将异常提示封装到到result
 result.setState(4000);
 result.setMessge("用户名被占用");
 } catch (InsertException e) {
 result.setState(5000);
 result.setMessge("注册时产生位置异常");
 }
 return result;
 }
 */
    /**
     * 2.接收数据方式:请求处理方法的参数列表设置为非pogjo类型
     * SpringBoot会直接将请求的参数名和方法的参数直接进行比较，
     * 如果名称相同则自动完成值的注入
     */
//    每次得到User对象，都需要对login发送请求，所以需要请求login的时候，
//    将User对象存储下来，使每个类都能访问这个对象
    //使用会话session
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.Login(username, password);
//   保存session，向sesssion对象中完成数据的绑定(session全局的)
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
//    测试：获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSesssion(session));
        return new JsonResult<User>(OK, data);
    }

    /**
     * 封装修改密码所需的json语句(交予前端的json语句)
     *
     * @param oldPassword 老密码
     * @param newPassword 新密码
     * @param session     用户首次提交的数据(登录)
     * @return JsonResutlt<Void>  交予前端的json语句
     */
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSesssion(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
//        user对象中四部分数据:username、phone、email、gender
//        uid没有，需要再次封装到user对象中
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSesssion(session);

        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    /**
     * 设置上传文件的最大值
     */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /**
     * 设置上传文件的类型
     */
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /**
     * MultipartFile,是springMVC提供的一个接口，这个接口包装了此类型的数据(任何类型的file)，springboot整合了springmvc,MultipartFile file自动将值赋予这个参数
     *
     * @param file
     * @param session 用户首次提交的数据(登录)
     * @return JsonResutlt<Void>  交予前端的json语句
     * @RequestParam("file") 表示请求中的参数，将请求参数注入请求处理方法的某个参数上，如果不一致，则强行映射
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {
//判断文件是否为null
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileEmptyException("文件超出限制");
        }
//          判断文件的类型是否是我们规定的后缀类型
        String contentType = file.getContentType();
//          如果集合包含某个元素则返回true
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("文件类型不支持");
        }
//        上传的文件../upload/文件.png(paret存放的目录)
        String parent = session.getServletContext().getRealPath("upload");
//        File对象指向这个路径，File是否存在
        File dir = new File(parent);
        if (!dir.exists()) {
//            不存在则创建目录
            dir.mkdir();
        }
//        获取文件名称，UUID工具类来生成新的字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("OriginalFilename=" + originalFilename);
//截取文件后缀
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
//        前缀(动态拼接新的名字)
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
//        创建空文件,将参数 @RequestParam("file") MultipartFile file 数据写入此文件中,两者后缀需要一致
        File dest = new File(dir, filename);
        try {
            file.transferTo(dest);
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            throw new FileUploadIOException("读写异常");
        }
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSesssion(session);
        String avatar = "/upload/" + filename;
        userService.changeAvatar(uid, avatar, username);
//        返回给用户头像的路径给前端，用于展示
        return new JsonResult<>(OK, avatar);
    }
}
