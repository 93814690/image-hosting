package top.liyf.imagehosting.service;

import org.springframework.web.multipart.MultipartFile;
import top.liyf.imagehosting.model.ImageSearchParam;

import java.util.Map;

/**
 * @author liyf
 * Created in 2018-12-27
 */
public interface ImageService {
    Map upload(MultipartFile file);

    Map getImagesList(ImageSearchParam searchParam, Integer pageNum, Integer pageSize);
}
