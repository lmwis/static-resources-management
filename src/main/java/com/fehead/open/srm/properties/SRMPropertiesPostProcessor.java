package com.fehead.open.srm.properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Description: 后置处理器
 * @Author: lmwis
 * @Date 2020-02-04 15:09
 * @Version 1.0
 */
@Component
public class SRMPropertiesPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof SRMProperties){ // 初始化配置文件中的图片上传路径
            SRMProperties srmProperties = (SRMProperties)bean;
            srmProperties.setUploadImageDir(initUploadImageDir(srmProperties));
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 根据系统类型设置磁盘存储路径
     * @return
     */
    private String initUploadImageDir(SRMProperties srmProperties) {
        String type = System.getProperty("os.name");
        String uploadImageDir;
        if (StringUtils.startsWithIgnoreCase(type, OSType.WINDOWS.getTypeStr())) { // 当前系统为windows
            uploadImageDir = srmProperties.getUploadImageDir2Windows();
        } else if (StringUtils.startsWithIgnoreCase(type, OSType.MAC.getTypeStr())) { // 为nac
            uploadImageDir = srmProperties.getUploadImageDir2Mac();
        } else { // 为linux
            uploadImageDir = srmProperties.getUploadImageDir2Linux();
        }
        return uploadImageDir;

    }
}
