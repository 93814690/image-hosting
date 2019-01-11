package top.liyf.imagehosting.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.liyf.imagehosting.dao.ImageDao;
import top.liyf.imagehosting.dao.OperationDao;
import top.liyf.imagehosting.exception.BusinessException;
import top.liyf.imagehosting.model.Image;
import top.liyf.imagehosting.model.ImageSearchParam;
import top.liyf.imagehosting.model.OperationLog;
import top.liyf.imagehosting.model.User;
import top.liyf.imagehosting.result.ResultCode;
import top.liyf.imagehosting.service.ImageService;
import top.liyf.imagehosting.util.AliyunOSSUtil;
import top.liyf.imagehosting.util.OperationUtil;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author liyf
 * Created in 2018-12-27
 */
@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final AliyunOSSUtil aliyunOSSUtil;

    private final ImageDao imageDao;

    private final OperationDao operationDao;

    @Autowired
    public ImageServiceImpl(AliyunOSSUtil aliyunOSSUtil, ImageDao imageDao, OperationDao operationDao) {
        this.aliyunOSSUtil = aliyunOSSUtil;
        this.imageDao = imageDao;
        this.operationDao = operationDao;
    }

    @Override
    public Map upload(MultipartFile file) {

        if (file.isEmpty()) {
            throw new BusinessException(ResultCode.UPLOAD_FAIL, "上传文件不能为空");
        }

        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        if (principal == null) {
            throw new BusinessException(ResultCode.NOT_LOGIN);
        }

        Image image = new Image();
        String imageId = UUID.randomUUID().toString();
        image.setImageId(imageId);
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        image.setImageName(fileName);

        image.setUid(principal.getUid());

        try {
            String url = aliyunOSSUtil.upload(file, imageId + suffixName);
            if (url == null) {
                throw new BusinessException(ResultCode.UPLOAD_FAIL);
            }
            image.setUrl(url);
        } catch (IOException e) {
            logger.error(principal.getUsername() + " 上传出现异常");
            logger.error(e.getMessage());
        }

        image.setCreateTime(new Date());
        imageDao.saveImage(image);

        OperationLog operationLog = OperationUtil.uploadImage(principal.getUsername(), image);
        operationDao.insertOperation(operationLog);

        HashMap<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("url", image.getUrl());
        resultMap.put("name", file.getOriginalFilename());

        return resultMap;
    }

    @Override
    public Map getImagesList(ImageSearchParam search, Integer pageNum, Integer pageSize) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        if (principal == null) {
            throw new BusinessException(ResultCode.NOT_LOGIN);
        }
        PageHelper.startPage(pageNum, pageSize);
        Page<Image> list =  imageDao.getImagesList(principal.getUid(), search);

        HashMap<String, Object> map = new HashMap<>(2);
        map.put("list", list.getResult());
        map.put("count", list.getTotal());

        return map;
    }
}
