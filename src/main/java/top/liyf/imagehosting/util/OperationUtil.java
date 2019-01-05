package top.liyf.imagehosting.util;

import top.liyf.imagehosting.model.Image;
import top.liyf.imagehosting.model.OperationLog;

/**
 * @author liyf
 * Created in 2018-12-31
 */
public class OperationUtil {

    public static OperationLog uploadImage(String username, Image image) {
        OperationLog operationLog = new OperationLog();
        operationLog.setOperator(username);
        operationLog.setOperationType("上传图片");
        operationLog.setOperation(image.getImageName() + "-" + image.getUrl());
        return operationLog;
    }
}
