package top.liyf.imagehosting.model;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liyf
 * Created in 2018-12-19
 */
@Data
public class User {

    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private Date registerTime;
    private Date loginTime;

    private Set<String> roles = new HashSet<>();
    private Set<String> perms = new HashSet<>();

    public String getCredentialsSalt() {
        return this.username +this.salt;
    }
}
