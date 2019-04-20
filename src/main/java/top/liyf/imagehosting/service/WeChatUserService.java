package top.liyf.imagehosting.service;

import top.liyf.imagehosting.model.WXLogin;

import java.util.Map;

/**
 * @author liyf
 * Created in 2019-04-04
 */
public interface WeChatUserService {

    Map<String, String> weChatLogin(WXLogin wxLogin) throws Exception;
}
