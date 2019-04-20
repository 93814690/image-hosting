package top.liyf.imagehosting.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liyf
 * Created in 2019-04-01
 */
@Component
@ConfigurationProperties(prefix = "wechat-mp")
@Data
public class WeChatMPProperties {

    private String appid;
    private String secret;
}
