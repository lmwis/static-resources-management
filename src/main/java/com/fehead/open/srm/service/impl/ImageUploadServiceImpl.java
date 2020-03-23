package com.fehead.open.srm.service.impl;

import com.fehead.open.srm.controller.ImageUploadController;
import com.fehead.open.srm.dao.RImage;
import com.fehead.open.srm.dao.mapper.RImageDOMapper;
import com.fehead.open.srm.properties.SRMProperties;
import com.fehead.open.srm.service.ImageUploadService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @Author: lmwis
 * @Date 2020-01-20 17:10
 * @Version 1.0
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    private final RImageDOMapper rImageDOMapper;

    // 存储图片服务器的公网ip
    private final String hostIp;
    // 服务器用户
    private final String username;
    // 应用端口
    private final String serverPort;
    // 前缀
    private final String imageUrlPre;

    private final SRMProperties sRMProperties;

    public ImageUploadServiceImpl(SRMProperties sRMProperties, RImageDOMapper rImageDOMapper, Environment env){
        this.rImageDOMapper = rImageDOMapper;
//        this.hostIp = Inet4Address.getLocalHost().getHostAddress();
        this.serverPort = env.getProperty("server.port");
        this.hostIp = sRMProperties.getHostIp();
        this.imageUrlPre = "http://"+hostIp+":"+serverPort+"/api/image";
        this.sRMProperties = sRMProperties;
        this.username = sRMProperties.getUsername();
    }

    /**
     * 写入数据库
     * @param filename 文件名/id
     * @return
     */
    @Override
    public RImage saveImagePath(String filename) {
        // 访问url
        String url = imageUrlPre+"/"+filename;
        // 硬盘上全路径
        String path = sRMProperties.getUploadImageDir()+"/"+filename;

        RImage rImage = new RImage(){{
            setId(filename); // 文件名做id
            setPath(path);
            setUrl(url);
            setHost(username+"@"+hostIp);
            setUploadTime(new Date());
            setStatus(1);
        }};

        System.out.println(new ReflectionToStringBuilder(rImage));

        rImageDOMapper.insert(rImage);

        return rImage;

    }
}
