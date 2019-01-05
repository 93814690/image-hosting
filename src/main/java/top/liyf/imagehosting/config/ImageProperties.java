package top.liyf.imagehosting.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liyf
 * Created in 2018-12-27
 */
@Component
@ConfigurationProperties(prefix = "image-properties")
@Data
public class ImageProperties {

    private String endpoint;
    private String keyid;
    private String keysecret;
    private String bucketname;



}
