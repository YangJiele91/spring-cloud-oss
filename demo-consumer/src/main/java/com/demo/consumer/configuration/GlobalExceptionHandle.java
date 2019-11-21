package com.demo.consumer.configuration;
import com.demo.consumer.enums.ExceptionCode;
import com.demo.common.exception.CommonExceptionCode;
import com.demo.common.exception.XXException;
import com.demo.common.model.Result;
import com.demo.common.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Void> handle(Exception e) {
        if (e instanceof XXException) {
            return ResultUtil.error(((XXException) e).getCode(), e.getMessage());
        } else if (e instanceof MissingServletRequestParameterException) {
            return ResultUtil.error(CommonExceptionCode.PARAM_INVALID.getCode(), CommonExceptionCode.PARAM_INVALID.getMessage());
        }
        logger.error(e.getMessage(),e);
        return ResultUtil.error(ExceptionCode.UNKNOWN_ERROR.getCode(), e.getMessage());
    }
}
