package top.liyf.imagehosting.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author liyf
 * Created in 2019-04-04
 */
public class WeChatUtil {

    private static final Logger logger = LoggerFactory.getLogger(WeChatUtil.class);

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";

    /**
     * 功能描述:
     *
     * @param encryptedData 加密的数据
     * @param sessionKey    解密秘钥
     * @param iv            偏移量
     * @return java.lang.String
     * @author liyf
     */
    public static String getUserInfo(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.getDecoder().decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.getDecoder().decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.getDecoder().decode(iv);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, KEY_ALGORITHM);
            AlgorithmParameters parameters = AlgorithmParameters.getInstance(KEY_ALGORITHM);
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化cipher
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                return new String(resultByte, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }


        return null;
    }
}
