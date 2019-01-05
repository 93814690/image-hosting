package top.liyf.imagehosting.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import top.liyf.imagehosting.ImageHostingApplication;

/**
 * @author liyf
 * Created in 2019-01-05
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ImageHostingApplication.class);
    }
}
