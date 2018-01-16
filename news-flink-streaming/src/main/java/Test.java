import com.xiaoniu.news.OSSUtil;
import com.xiaoniu.news.uploadoos.uploadoosImpl.UploadOOSFileImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author wuzhiguo
 * @create 2018-01-12:18:52
 * @desc Demo
 ***/
public class Test {

    private static Logger logger = LoggerFactory.getLogger(UploadOOSFileImpl.class);

    public static void main(String[] args) throws Exception {
        System.out.println("1111111111111111");

        new Test().demo();

    }

    public void demo() throws Exception {

//        UploadOOSFile uploadOOSFile = new UploadOOSFileImpl();

//        File file = new File("D:\\workspace\\ideaproject_sd\\news-flink-parent\\news-flink-streaming\\src\\test\\resources\\news_flatsample_simplenews.json");
//
//        logger.info("static read local File >>>>>>>>>>>>>>>>>>>  = {}",file);
//
//        if(!uploadOOSFile.checkFile(file)){
//            logger.info("file not null",file);
//        }
//
//        System.out.print("name ===================="+file.getName());
//
//        byte[] srtbyte = file.getName().getBytes();

        String str = "Hello World";

        InputStream in = new ByteArrayInputStream(str.getBytes());

        String putObjectResult = new OSSUtil().putObject("news-source-oss/news/demo.html",in);

        System.out.println(putObjectResult);

    }

}
