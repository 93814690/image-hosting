package top.liyf.imagehosting.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.liyf.imagehosting.config.WeChatMPProperties;
import top.liyf.imagehosting.constant.MiniProgram;
import top.liyf.imagehosting.dao.WeChatUserDao;
import top.liyf.imagehosting.model.HttpClientResult;
import top.liyf.imagehosting.model.WXLogin;
import top.liyf.imagehosting.model.WeChatUser;
import top.liyf.imagehosting.service.WeChatUserService;
import top.liyf.imagehosting.util.HttpClientUtil;
import top.liyf.imagehosting.util.UUIDUtil;
import top.liyf.imagehosting.util.WeChatUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liyf
 * Created in 2019-04-04
 */
@Service
public class WeChatUserServiceImpl implements WeChatUserService {

    private static final Logger logger = LoggerFactory.getLogger(WeChatUserServiceImpl.class);

    private final WeChatMPProperties weChatMPProperties;
    private final WeChatUserDao weChatUserDao;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public WeChatUserServiceImpl(WeChatMPProperties weChatMPProperties, WeChatUserDao weChatUserDao, StringRedisTemplate stringRedisTemplate) {
        this.weChatMPProperties = weChatMPProperties;
        this.weChatUserDao = weChatUserDao;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public Map<String, String> weChatLogin(WXLogin wxLogin) throws Exception {

        HttpClientResult httpClientResult = code2Session(wxLogin);

        JSONObject jsonObject = JSONObject.parseObject(httpClientResult.getContent());
        String openid = (String) jsonObject.get("openid");
        String sessionKey = (String) jsonObject.get("session_key");

        // save to redis
        String uuid = UUIDUtil.get();
        stringRedisTemplate.opsForValue().set(uuid, openid, 3, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(openid, uuid, 3, TimeUnit.DAYS);

        Map<String, String> map = new HashMap<>(2);
        map.put("sessionKey", uuid);

        //  判断用户是否存在
        WeChatUser weChatUser = weChatUserDao.getUserByOpenId(openid);

        if (weChatUser == null) {
            map.put("activation", "0");
            //  数据签名校验
            String sha1Hex = DigestUtils.sha1Hex(wxLogin.getRawData() + sessionKey);
            boolean signatureResult = sha1Hex.equals(wxLogin.getSignature());
            if (signatureResult) {
                //  加密数据解密
                String userInfo = WeChatUtil.getUserInfo(wxLogin.getEncryptedData(), sessionKey, wxLogin.getIv());
                WeChatUser newWeChatUser = JSONObject.parseObject(userInfo, WeChatUser.class);

                if (userInfo != null) {
                    newWeChatUser.setCreateTime(LocalDateTime.now());
                    newWeChatUser.setLoginTime(LocalDateTime.now());
                    newWeChatUser.setUpdateTime(LocalDate.now());

                    int i = weChatUserDao.saveWeChatUser(newWeChatUser);
                    if (i != 1) {
                        logger.error("用户保存失败");
                        logger.error(newWeChatUser.toString());
                    }
                }
            }
        } else {
            weChatUser.setLoginTime(LocalDateTime.now());
            weChatUserDao.updateLoginTime(weChatUser);

            map.put("activation", weChatUser.getActivation().toString());

        }

        return map;
    }

    private HttpClientResult code2Session(WXLogin wxLogin) throws Exception {
        Map<String, String> params = new HashMap<>(4);
        params.put("appid", weChatMPProperties.getAppid());
        params.put("secret", weChatMPProperties.getSecret());
        params.put("js_code", wxLogin.getCode());
        params.put("grant_type", MiniProgram.GRANT_TYPE);
        return HttpClientUtil.doGet(MiniProgram.WX_LOGIN_URL, params);
    }


}
