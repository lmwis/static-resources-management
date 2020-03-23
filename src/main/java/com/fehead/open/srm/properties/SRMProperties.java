package com.fehead.open.srm.properties;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author: lmwis
 * @Date 2020-01-20 17:21
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "static-resources-management")
public class SRMProperties {

    private String uploadImageDir;

    private String hostIp;

    private String username;

    private String uploadImageDir2Mac = "/Users/lmwis/IdeaProjects/static-resources-management/user_images";

    private String uploadImageDir2Windows = "D:/static-resources-management/user_images";

    private String uploadImageDir2Linux = "/home/static-resources-management/images";




}
