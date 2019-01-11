package top.liyf.imagehosting.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.liyf.imagehosting.model.Image;
import top.liyf.imagehosting.model.ImageSearchParam;

/**
 * @author liyf
 * Created in 2018-12-27
 */
@Repository
public interface ImageDao {

    void saveImage(Image image);

    @Select("select * from t_image where uid=#{uid} and status=1 and image_name like '%${search.name}%'" +
            "order by create_time desc")
    @Results({
            @Result(property = "imageId", column = "image_id"),
            @Result(property = "imageName", column = "image_name"),
            @Result(property = "createTime", column = "create_time")
    })
    Page<Image> getImagesList(@Param("uid") Integer uid, @Param("search") ImageSearchParam search);
}
