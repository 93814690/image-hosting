package top.liyf.imagehosting.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author liyf
 * Created in 2019-04-03
 */
@Data
public class WeChatUser implements Serializable {

    private Integer id;
    private Integer uid;
    private String openId;
    private String nickName;
    private String avatarUrl;
    private Integer gender;
    private String country;
    private String province;
    private String city;
    private Integer activation;
    private LocalDateTime createTime;
    private LocalDateTime loginTime;
    private LocalDate updateTime;

}
