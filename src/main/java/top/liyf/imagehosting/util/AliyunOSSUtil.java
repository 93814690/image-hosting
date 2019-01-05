package top.liyf.imagehosting.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.liyf.imagehosting.config.ImageProperties;
import top.liyf.imagehosting.model.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author liyf
 * Created in 2018-12-27
 */
@Component
public class AliyunOSSUtil {

    private static final Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);

    private final ImageProperties imageProperties;

    @Autowired
    public AliyunOSSUtil(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }

    public String upload(MultipartFile file, String fileName) throws IOException {

        User principal = (User) SecurityUtils.getSubject().getPrincipal();

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(imageProperties.getEndpoint(),
                imageProperties.getKeyid(), imageProperties.getKeysecret());

        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（fileName）。
        PutObjectResult result = ossClient.putObject(imageProperties.getBucketname(),
                fileName, new ByteArrayInputStream(file.getBytes()));

        // 关闭OSSClient。
        ossClient.shutdown();

        if (result != null) {
            return "https://" + imageProperties.getBucketname() + "." + imageProperties.getEndpoint() + "/" + fileName;
        }
        return null;
    }
}
