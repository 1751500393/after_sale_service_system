package com.cen.after_sale_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;

@SpringBootApplication
//MapperScan指定当前项目中的Mapper接口路径位置.让mapper接口自动加载
@MapperScan("com.cen.after_sale_service.mapper")
public class AfterSaleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfterSaleServiceApplication.class, args);

    }

}
