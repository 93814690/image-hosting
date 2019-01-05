package top.liyf.imagehosting.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.liyf.imagehosting.model.User;

/**
 * @author liyf
 * Created in 2018-12-19
 */
@Controller
public class HomeController {

    @RequestMapping({"/","/index"})
    public String index(Model model) {

        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println("principal = " + principal);

        return "index";
    }


    @GetMapping("/test2")
    @RequiresPermissions("systemUserAdd")
    public String test2() {

        System.out.println("test2");

        return "test2";
    }

    @GetMapping("/test3")
    @RequiresPermissions("test3")
    public String test3() {

        System.out.println("test3");

        return "test3";
    }
}
