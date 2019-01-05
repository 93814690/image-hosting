package top.liyf.imagehosting.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import top.liyf.imagehosting.model.OperationLog;

/**
 * @author liyf
 * Created in 2018-12-31
 */
@Repository
public interface OperationDao {

    @Insert("insert into t_operation(operator,operation_type,operation,operation_time)" +
            "values(#{operator},#{operationType},#{operation},now())")
    void insertOperation(OperationLog operationLog);
}
