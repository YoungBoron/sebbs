package com.rgsj3.sebbs.handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgsj3.sebbs.domain.Result;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CheckAdvice {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 请求的 JSON 参数在请求体内的参数校验
     *
     * @param e 异常信息
     * @return 返回数据
     * @throws JsonProcessingException jackson 的异常
     */
    @ExceptionHandler(TransactionSystemException.class)
    public Result handleBindException1(TransactionSystemException e) throws JsonProcessingException {
        Throwable t = e.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        if (t instanceof ConstraintViolationException) {
            List<String> msgList = new ArrayList<>();
            for (ConstraintViolation<?> constraintViolation : ((ConstraintViolationException)t).getConstraintViolations()) {
                msgList.add(constraintViolation.getMessage());
            }
            String messages = StringUtils.join(msgList.toArray(), ";");
            return Result.error(2, messages);
        } else {
            return Result.error(5, "未知错误");
        }
    }

}