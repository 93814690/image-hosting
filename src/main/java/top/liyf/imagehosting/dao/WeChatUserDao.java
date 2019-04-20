package top.liyf.imagehosting.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.liyf.imagehosting.model.WeChatUser;

/**
 * @author liyf
 * Created in 2019-04-04
 */
@Repository
public interface WeChatUserDao {

    @Select("select * from wechat_user where open_id=#{openId}")
    WeChatUser getUserByOpenId(String openId);

    int saveWeChatUser(WeChatUser weChatUser);

    @Update("update wechat_user set login_time=#{loginTime} where open_id=#{openId}")
    void updateLoginTime(WeChatUser weChatUser);
}
