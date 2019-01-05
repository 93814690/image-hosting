package top.liyf.imagehosting.result;

/**
 * @author liyf
 * Created in 2018\12\31
 */
public enum ResultCode {

    /** 成功 */
    SUCCESS(200, "成功"),

    /** 操作失败 */
    FAIL(400, "操作失败"),

    /** 没有登录 */
    NOT_LOGIN(-1, "没有登录"),

    /** 数据已存在 */
    DATA_ALREADY_EXISTS(401, "数据已存在"),

    /** 参数错误 */
    PARAMS_ERROR(402, "参数错误 "),

    /** 不支持或已经废弃 */
    NOT_SUPPORTED(403, "不支持或已经废弃"),

    /** 太频繁的调用 */
    TOO_FREQUENT(405, "太频繁的调用"),

    /** 上传失败 */
    UPLOAD_FAIL(406, "上传失败"),

    /** 发生异常 */
    EXCEPTION(502, "发生异常"),

    /** 系统错误 */
    SYS_ERROR(501, "系统错误"),

    /** 未知的错误 */
    UNKNOWN_ERROR(500, "未知错误");

    ResultCode(int value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public int val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private int val;
    private String msg;

}
