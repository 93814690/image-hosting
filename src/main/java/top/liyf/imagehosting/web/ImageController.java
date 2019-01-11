package top.liyf.imagehosting.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.liyf.imagehosting.model.ImageSearchParam;
import top.liyf.imagehosting.result.ResultBean;
import top.liyf.imagehosting.service.ImageService;

import java.util.Map;

/**
 * @author liyf
 * Created in 2018-12-27
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResultBean<Map> upload(@RequestParam("file") MultipartFile file) {
        return new ResultBean<>(imageService.upload(file));

    }

    @GetMapping("/imagesList")
    public String toImagesList() {
        return "imagesList";
    }

    @PostMapping("/imagesList")
    @ResponseBody
    public ResultBean<Map> getImagesList(String params, Integer pageNum, Integer pageSize) {

        ImageSearchParam search = JSONObject.parseObject(params, ImageSearchParam.class);
        return new ResultBean<>(imageService.getImagesList(search, pageNum, pageSize));
    }


}
