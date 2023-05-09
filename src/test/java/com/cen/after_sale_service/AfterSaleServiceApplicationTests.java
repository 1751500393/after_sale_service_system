package com.cen.after_sale_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class AfterSaleServiceApplicationTests {
    @Autowired(required = false)//自动装配用于创建类
// 接口类是不能直接创建bean的,就是说不能直接创建对象，（但可以通过在方法内创建返回值为dataSource的方法，创建对象），所以这里会产生检验错误 @Autowired(required = false)解决错误
    private DataSource dataSource;
    @Test
    void contextLoads() {
    }
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }


}
