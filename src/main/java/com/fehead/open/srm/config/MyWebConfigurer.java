package com.fehead.open.srm.config;

import com.fehead.open.srm.controller.ImageUploadController;
import com.fehead.open.srm.properties.SRMProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: lmwis
 * @Date 2020-01-20 17:03
 * @Version 1.0
 */
@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Autowired
    private SRMProperties sRMProperties;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/image/**").addResourceLocations("file:" + sRMProperties.getUploadImageDir()+"/");
    }

    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("**").allowedOrigins("*");
    }
}
