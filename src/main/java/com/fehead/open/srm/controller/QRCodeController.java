package com.fehead.open.srm.controller;

import com.fehead.lang.componment.QRCodeGenerator;
import com.fehead.lang.response.CommonReturnType;
import com.fehead.lang.response.FeheadResponse;
import com.fehead.open.srm.dao.RImage;
import com.fehead.open.srm.service.ImageUploadService;
import com.fehead.open.srm.service.impl.ImageUploadServiceImpl;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Description: 二维码上传控制器
 * @Author: lmwis
 * @Date 2020-03-23 12:13
 * @Version 1.0
 */

@RestController
@RequestMapping("/code")
@RequiredArgsConstructor
public class QRCodeController {

    final QRCodeGenerator qrCodeGenerator;

    final ImageUploadController imageUploadController;

    final ImageUploadService imageUploadService;

    @PostMapping()
    public FeheadResponse createQRCode(String content) throws WriterException, IOException {

        // 将内容生成二维码
        BufferedImage bufferedImage = qrCodeGenerator.generateQRCode(content);
        ByteArrayOutputStream ots = new ByteArrayOutputStream();
        // 写入字节流
        ImageIO.write(bufferedImage, "png", ots);
        // 转化为文件
        MultipartFile file = new MockMultipartFile("code","code.png","",ots.toByteArray());

        // 文件写入本地
        String fileTotalName = imageUploadController.saveFile2Host(file);
        // 写入数据库
        RImage rImage = imageUploadService.saveImagePath(fileTotalName);

        return CommonReturnType.create(rImage);
    }
}
