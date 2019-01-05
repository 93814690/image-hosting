package top.liyf.imagehosting.model;

import lombok.Data;

import java.util.Date;

/**
 * @author liyf
 * Created in 2018-12-31
 */
@Data
public class OperationLog {

    private Long id;
    private String operator;
    private String operationType;
    private String operation;
    private Date operationTime;
}
