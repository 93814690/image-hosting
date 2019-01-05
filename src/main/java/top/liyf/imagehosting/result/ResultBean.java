package top.liyf.imagehosting.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liyf
 * @date Created in 2018\12\31
 */
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code = ResultCode.SUCCESS.val();
    private String msg = ResultCode.SUCCESS.msg();
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(ResultCode code) {
        super();
        this.code = code.val();
        this.msg = code.msg();
    }

    public ResultBean(ResultCode code, String msg) {
        super();
        this.code = code.val();
        this.msg = msg;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = ResultCode.EXCEPTION.val();
    }
}
