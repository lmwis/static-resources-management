package com.fehead.open.srm;

import com.fehead.lang.componment.StringIdGenerator;
import com.fehead.open.srm.dao.RImage;
import com.fehead.open.srm.properties.SRMProperties;
import com.fehead.open.srm.service.impl.ImageUploadServiceImpl;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: lmwis
 * @Date 2020-01-31 20:13
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
//@ComponentScan("com.fehead.open.srm")
@SpringBootTest(classes = StaticResourcesManagementApplication.class)
public class PropertiesTest {

    @Autowired
    StringIdGenerator stringIdGenerator;

    @Autowired
    ImageUploadServiceImpl imageUploadService;
//    @Autowired
//    SRMProperties srmProperties;

    @Test
    public void checkProperties(){
        String filename = stringIdGenerator.generatorId();
//        System.out.println(filename);
//        RImage rImage = imageUploadService.saveImagePath(filename);
//        System.out.println(new ReflectionToStringBuilder(rImage));
    }
}
