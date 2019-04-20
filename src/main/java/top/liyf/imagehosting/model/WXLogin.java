package top.liyf.imagehosting.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liyf
 * Created in 2019-04-01
 */
@Data
public class WXLogin implements Serializable {

    private String code;
    private String rawData;
    private String signature;
    private String encryptedData;
    private String iv;
}
