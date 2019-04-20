package top.liyf.imagehosting.util;

import java.util.UUID;

/**
 * @author liyf
 * Created in 2019-04-05
 */
public class UUIDUtil {

    public static String get() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
