package top.liyf.imagehosting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.liyf.imagehosting.dao")
public class ImageHostingApplication{

    public static void main(String[] args) {
        SpringApplication.run(ImageHostingApplication.class, args);
    }

}

