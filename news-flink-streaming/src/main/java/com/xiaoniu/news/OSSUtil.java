package com.xiaoniu.news;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/***
 * 
 地址：pre-sudai.oss-cn-shanghai.aliyuncs.com 目录：待创建 AccessKeyId：LTAIxA8ItZBUI8uk
 * AccessKeySecret：cELUEaY9S7rwGBc7N0AKNbnQLnpI9H
 * 
 */
public class OSSUtil {
	private static Logger logger = LoggerFactory.getLogger(OSSUtil.class);
	//正是环境

	static String endPoint = PropertiesUtil.getKey("aliyun.endPoint");

	static String accessKeyId = PropertiesUtil.getKey("aliyun.accessKeyId");

	static String accessKeySecret = PropertiesUtil.getKey("aliyun.accessKeySecret");

	static String bucketName = PropertiesUtil.getKey("aliyun.bucketName");

	public static String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		OSSUtil.accessKeyId = accessKeyId;
	}

	public static String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		OSSUtil.accessKeySecret = accessKeySecret;
	}

	public static String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		OSSUtil.endPoint = endPoint;
	}

	public static  OSSClient getOSSClient() {
		OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
		return ossClient;
	}

	/**
	 * 新建Bucket --Bucket权限:私有
	 * 
	 * @param bucketName
	 *            bucket名称
	 * @return true 新建Bucket成功
	 */
	public static final boolean createBucket(OSSClient client, String bucketName) {
		Bucket bucket = client.createBucket(bucketName);
		return bucketName.equals(bucket.getName());
	}

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

	/**
	 * 向阿里云的OSS存储中存储文件 --file也可以用InputStream替代
	 * 
	 * @param client
	 *            OSS客户端
	 * @param file
	 *            上传文件
	 * @param bucketName
	 *            bucket名称
	 * @param diskName
	 *            上传文件的目录 --bucket下文件的路径
	 * @return String 唯一MD5数字签名
	 */
	public static final boolean uploadObject2OSS(OSSClient client, File file, String bucketName, String diskName) throws FileNotFoundException {
		InputStream is = new FileInputStream(file);
		try {
			String fileName = file.getName();
			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(is.available());
			metadata.setCacheControl("no-cache");
			metadata.setHeader("Pragma", "no-cache");
			metadata.setContentEncoding("UTF-8");
			metadata.setContentType(getContentType(fileName));
			// 上传文件
			PutObjectResult putObjectResult = client.putObject(bucketName, diskName + "/" + fileName, is, metadata);

			if(putObjectResult.getETag() == null){
				System.out.println("putObjectResult ==================="+putObjectResult);
				return false;
			}else{
				return true;
			}

		} catch (Exception e) {
			logger.error("上传文件到OSS失败", e);
			return false;
		} finally {
			if (null != is) {
				logger.info("关闭文件的输入流！");
				try {
					is.close();
				} catch (Exception e) {
					logger.error("关闭文件的输入流异常", e);
				}
			}
		}
	}

	public static boolean upload(String message, String fileName) {

		try {
			OSSClient ossClient = getOSSClient();
			InputStream is = new ByteArrayInputStream(message.getBytes("utf-8"));
			int length = 0;
			length = is.available();
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setCacheControl("no-cache");
			metadata.setContentLength(length);
			metadata.setHeader("Pragma", "no-cache");
			metadata.setContentEncoding("UTF-8");
			metadata.setContentType(getContentType(fileName));
			ossClient.putObject(bucketName, "news-source-oss/news/" + fileName, is, metadata);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	/**
	 * 向阿里云的OSS存储中存储文件 --file也可以用InputStream替代
	 * 
	 * @param client
	 *            OSS客户端
	 *            上传文件
	 * @param bucketName
	 *            bucket名称
	 * @param diskName
	 *            上传文件的目录 --bucket下文件的路径
	 * @return String 唯一MD5数字签名
	 */
	public static final boolean uploadInputStreamObject2OSS(OSSClient client,InputStream is ,String fileName, String bucketName, String diskName) throws FileNotFoundException {
		try {
			// 创建上传Object的Metadata
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(is.available());
			metadata.setCacheControl("no-cache");
			metadata.setHeader("Pragma", "no-cache");
			metadata.setContentEncoding("UTF-8");
			metadata.setContentType(getContentType(fileName));
			PutObjectResult putResult =	client.putObject(bucketName, diskName + "/" + fileName, is, metadata);
			logger.info("aliyun return >>>>>>>>>>>>>>>>>>>>>>> Info  = {}",putResult.getETag());
		} catch (Exception e) {
			logger.error("上传文件到OSS失败", e);
			return false;
		} finally {
			if (null != is) {
				logger.info("关闭文件的输入流！");
				try {
					is.close();
				} catch (Exception e) {
					logger.error("关闭文件的输入流异常", e);
				}
			}
		}
		return true;
	}

	/**
	 * &#x6839;&#x636e;key&#x83b7;&#x53d6;OSS&#x670d;&#x52a1;&#x5668;&#x4e0a;&#x7684;&#x6587;&#x4ef6;&#x5230;&#x672c;&#x5730;
	 *
	 * @param client
	 *            OSS&#x5ba2;&#x6237;&#x7aef;
	 * @param bucketName
	 *            &#x6587;&#x4ef6;&#x8def;&#x5f84;
	 * @param key
	 *            Bucket&#x4e0b;&#x7684;&#x6587;&#x4ef6;&#x7684;&#x8def;&#x5f84;&#x540d;+&#x6587;&#x4ef6;&#x540d;
	 */
	public static final void getOSS2LocalFile(OSSClient client, String bucketName, String yourLocalFile, String key) {
		client.getObject(new GetObjectRequest(bucketName, key), new File(yourLocalFile));
	}

	/**
	 * 根据key获取OSS服务器上的文件输入流
	 * 
	 * @param client
	 *            OSS客户端
	 * @param bucketName
	 *            bucket名称
	 * @param diskName
	 *            文件路径
	 * @param key
	 *            Bucket下的文件的路径名+文件名
	 */
	public static final InputStream getOSS2InputStream(OSSClient client, String bucketName, String diskName, String key) {
		OSSObject ossObj = client.getObject(bucketName, diskName + key);
		return ossObj.getObjectContent();
	}

	/**
	 * 根据key删除OSS服务器上的文件
	 * 
	 * @param client
	 *            OSS客户端
	 * @param bucketName
	 *            bucket名称
	 * @param diskName
	 *            文件路径
	 * @param key
	 *            Bucket下的文件的路径名+文件名
	 */
	public static void deleteFile(OSSClient client, String bucketName, String diskName, String key) {
		client.deleteObject(bucketName, diskName + key);
		logger.info("删除" + bucketName + "下的文件" + diskName + key + "成功");
	}

	/**
	 * 通过文件名判断并获取OSS服务文件上传时文件的contentType
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件的contentType
	 */
	public static final String getContentType(String fileName) {
		String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
		if ("bmp".equalsIgnoreCase(fileExtension))
			return "image/bmp";
		if ("gif".equalsIgnoreCase(fileExtension))
			return "image/gif";
		if ("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension) || "png".equalsIgnoreCase(fileExtension))
			return "image/jpeg";
		if ("html".equalsIgnoreCase(fileExtension))
			return "text/html";
		if ("txt".equalsIgnoreCase(fileExtension))
			return "text/plain";
		if ("vsd".equalsIgnoreCase(fileExtension))
			return "application/vnd.visio";
		if ("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension))
			return "application/vnd.ms-powerpoint";
		if ("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension))
			return "application/msword";
		if ("xml".equalsIgnoreCase(fileExtension))
			return "text/xml";
		return "text/html";
	}


	/**
	 * 将key-value存入阿里对象存储
	 * @param key
	 * @param is 经过base64加密的图片string
	 * @return 新创建的{@link OSSObject}的ETag值。  如果出错，则返回null
	 */
	public String putObject(String key,InputStream is){
		String resultStr = null;
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(is.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentEncoding("utf-8");
			System.out.print("buckNmae==========="+bucketName);
			objectMetadata.setContentType(getContentType("news_flatsample_simplenews.json"));

			System.out.print("buckNmae==========="+bucketName);
			//上传文件
			PutObjectResult putResult = getOSSClient().putObject(bucketName, key, is, objectMetadata);
			//解析结果
			resultStr = putResult.getETag();
			logger.info("successfully put object to OSS with key = {}",resultStr);
		} catch (Exception e) {
			logger.error("上传阿里云OSS服务器异常."+ e.getMessage(), e);
		}
		return resultStr;
	}
}
