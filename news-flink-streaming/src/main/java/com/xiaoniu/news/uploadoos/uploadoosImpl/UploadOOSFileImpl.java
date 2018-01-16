package com.xiaoniu.news.uploadoos.uploadoosImpl;
import com.aliyun.oss.OSSClient;
import com.xiaoniu.news.uploadoos.UploadOOSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wuzhiguo
 * @create 2018-01-12:17:58
 * @desc 检查上传OOS服务
 ***/
@Service
public class UploadOOSFileImpl implements UploadOOSFile {

    private static Logger logger = LoggerFactory.getLogger(UploadOOSFileImpl.class);


    /**
     * 删除Bucket
     *
     * @param bucketName
     *            bucket名称
     */
    public static final void deleteBucket(OSSClient client, String bucketName) {
        client.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    @Override
    public boolean checkFile(File file) {

        if(file.exists()){
            logger.info("get file seccess", file);
           return true;
        }else{
            logger.info("file not exists, create it ...", file);
            return false;
        }
    }

    @Override
    public String fileNameFormat(String param) {
        return null;
    }

    @Override
    public boolean checkFailFlag(String param) {

        if(StringUtils.isEmpty(param)){
            return  false;
        }
        return true;
    }

}
