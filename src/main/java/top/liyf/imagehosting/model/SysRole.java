package top.liyf.imagehosting.model;

import lombok.Data;

/**
 * @author liyf
 * Created in 2018-12-20
 */
@Data
public class SysRole {

    private Integer rid;
    private String role;
    private String description;
    private Boolean available;
}
