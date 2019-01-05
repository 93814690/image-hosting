package top.liyf.imagehosting.model;

import lombok.Data;

import java.util.Date;

/**
 * @author liyf
 * Created in 2018-12-27
 */
@Data
public class Image {

    private String imageId;
    private String imageName;
    private Integer uid;
    private String url;
    private Date createTime;
    private Integer status;
}
