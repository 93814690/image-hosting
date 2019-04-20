package top.liyf.imagehosting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liyf.imagehosting.model.WXLogin;
import top.liyf.imagehosting.result.ResultBean;
import top.liyf.imagehosting.service.WeChatUserService;

import java.util.Map;

/**
 * @author liyf
 * Created in 2019-03-30
 */
@RestController
@RequestMapping("/mp")
public class MiniPController {

    private final WeChatUserService weChatUserService;

    @Autowired
    public MiniPController(WeChatUserService weChatUserService) {
        this.weChatUserService = weChatUserService;
    }

    @PostMapping("/login")
    public ResultBean<Map> login(@RequestBody WXLogin wxLogin) throws Exception {

        return new ResultBean<>(weChatUserService.weChatLogin(wxLogin));
    }
}
