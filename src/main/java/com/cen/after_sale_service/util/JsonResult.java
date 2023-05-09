package com.cen.after_sale_service.util;

import java.io.Serializable;
import java.util.Properties;

/**
 * Json格式的数据响应
 */
//序列化接口，用于对象流
public class JsonResult<E> implements Serializable {
    /**
     * 状态码
     */
    private Integer state;
    /**
     * 描述信息
     */
    private String messge;
    /**
     * 接受数据但不清楚数据类型，所以泛型
     */
    private E data;

    //构造方法
    public JsonResult() {

    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    //   接受报错
    public JsonResult(Throwable e) {
        this.messge = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    //get&&set
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
