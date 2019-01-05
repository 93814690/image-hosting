package top.liyf.imagehosting.aop;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.liyf.imagehosting.exception.BusinessException;
import top.liyf.imagehosting.model.User;
import top.liyf.imagehosting.result.ResultBean;

/**
 * @author liyf
 * Created in 2018-12-31
 */
@Aspect
@Component
@Order(-1)
public class ResultBeanAOP {

    private static final Logger logger = LoggerFactory.getLogger(ResultBeanAOP.class);

    @Pointcut("execution(public top.liyf.imagehosting.result.ResultBean *(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;

        try {
            result = (ResultBean<?>) joinPoint.proceed();
            logger.info(joinPoint.getSignature() + ": use time: " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Throwable throwable) {
            result = handlerException(joinPoint, throwable);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint joinPoint, Throwable e) {

        String msg = "error:";
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user != null) {
            msg += user.getUid() + "-" + user.getUsername() + "-";
        }
        // 已知异常
        if (e instanceof BusinessException) {
            BusinessException exception = (BusinessException) e;
            logger.error(msg + exception.getMsg());
            return new ResultBean<>(exception.getCode(), exception.getMsg());
        }

        logger.error(joinPoint.getSignature() + " error ", e);
        // 未知的异常，应该格外注意，可以发送邮件通知等


        return new ResultBean<>(e);
    }
}
