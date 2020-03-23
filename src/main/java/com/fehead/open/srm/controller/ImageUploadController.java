package com.fehead.open.srm.controller;



import com.fehead.lang.componment.StringIdGenerator;
import com.fehead.lang.controller.BaseController;
import com.fehead.lang.error.BusinessException;
import com.fehead.lang.error.EmBusinessError;
import com.fehead.lang.response.CommonReturnType;
import com.fehead.lang.response.FeheadResponse;
import com.fehead.open.srm.dao.RImage;
import com.fehead.open.srm.properties.SRMProperties;
import com.fehead.open.srm.service.ImageUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description:
 * @Author: lmwis
 * @Date 2020-01-20 17:01
 * @Version 1.0
 */

@RestController
@RequestMapping("/image")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ImageUploadController extends BaseController {

    private final SRMProperties srmProperties;

    /**
     * UUID生成器
     */
    private final StringIdGenerator stringIdGenerator;

    private ImageUploadService imageUploadService;

    public ImageUploadController(StringIdGenerator stringIdGenerator,SRMProperties srmProperties,ImageUploadService imageUploadService){
        this.stringIdGenerator = stringIdGenerator;
        this.srmProperties = srmProperties;
        this.imageUploadService = imageUploadService;
    }

    @PostMapping()
    public FeheadResponse faceImageUpload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"文件为空");
        }
        // 本地文件写入
        String fileTotalName = saveFile2Host(file);

        // 写入数据库
        RImage rImage = imageUploadService.saveImagePath(fileTotalName);

        return CommonReturnType.create(rImage);

    }

    /**
     * 文件写入本地
     * @param file
     * @return 文件名
     */
    private String saveFile2Host(MultipartFile file) {
        // 文件名
        String fileName = stringIdGenerator.generatorId();
        // 原来文件后缀
        String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 磁盘存储路径
        String totalPath = srmProperties.getUploadImageDir() +"/"+ fileName + suffixName;
        File dest = new File(totalPath);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 文件写入
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName+suffixName;
    }



}


