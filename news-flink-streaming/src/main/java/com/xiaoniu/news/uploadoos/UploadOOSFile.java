package com.xiaoniu.news.uploadoos;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wuzhiguo
 * @create 2018-01-12:17:58
 * @desc 检查上传OOS服务
 ***/
public interface UploadOOSFile {

    /**
     * 检查文件是否存在
     * @return
     */
    public boolean checkFile(File file);

    /**
     * 检查文件命名是否正确
     * @return
     */
    public String fileNameFormat(String param);

    /**
     * 检查文件是否上传成功
     * @param name
     * @return
     */
    public boolean checkFailFlag(String name);
}
