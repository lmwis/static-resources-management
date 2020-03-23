package com.fehead.open.srm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: lmwis
 * @Date 2020-01-20 16:17
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan({"com.fehead.lang","com.fehead.open.srm"})
@MapperScan("com.fehead.open.srm.dao.mapper")
public class StaticResourcesManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticResourcesManagementApplication.class,args);
    }
}
