package top.liyf.imagehosting.exception;

import lombok.Data;
import top.liyf.imagehosting.result.ResultCode;

import java.io.Serializable;

/**
 * @author liyf
 * Created in 2019-01-03
 */
@Data
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;
    private String msg;
    private ResultCode code;

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BusinessException(ResultCode code) {
        super(code.msg());
        this.msg = code.msg();
        this.code = code;
    }

    public BusinessException(ResultCode code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
