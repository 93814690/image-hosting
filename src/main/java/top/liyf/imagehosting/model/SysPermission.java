package top.liyf.imagehosting.model;

import lombok.Data;

/**
 * @author liyf
 * Created in 2018-12-20
 */
@Data
public class SysPermission {

    private Integer pid;
    private String name;

    //资源类型，[menu|button]
    private String resourceType;
    private String url;
    private String permission;
    private Long parentId;
    private Integer available = 1;
}
