package com.fehead.open.srm.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: lmwis
 * @Date 2020-01-20 17:09
 * @Version 1.0
 */
@Data
@TableName("srm_upload_image")
public class RImage {

    /**
     * 图片全局唯一UUID
     */
    @TableId
    private String id;

    /**
     * 网络访问url
     */
    @TableField("url")
    private String url;

    /**
     * 在服务器上存放路径
     */
    @TableField("path")
    private String path;

    /**
     * 服务器用户和ip
     * exp: root@47.92.194.26
     */
    @TableField("host")
    private String host;

    /**
     * 上传时间
     */
    @TableField("upload_time")
    private Date uploadTime;

    /**
     * 数据状态：
     *  1: 正常
     *  2: 不可访问
     *  3: 异常
     */
    @TableField("status")
    private int status;


}
