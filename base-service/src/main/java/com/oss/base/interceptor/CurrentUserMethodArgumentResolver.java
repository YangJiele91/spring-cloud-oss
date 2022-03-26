package com.oss.base.interceptor;

import com.oss.common.annotate.CurrentUser;
import com.oss.common.constant.RedisKey;
import com.oss.common.model.LoginUser;
import com.oss.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用于绑定@CurrentUser的方法参数解析器
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private RedisUtil redisUtil;

    public CurrentUserMethodArgumentResolver() {
    }

    /**
     * 校验入参是否包含注解
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().isAssignableFrom(LoginUser.class) && parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    /**
     * 写入登录信息到入参
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        LoginUser loginUser = null;
        String token = webRequest.getHeader("Authorization");
        if (token != null) {
            loginUser = redisUtil.get(RedisKey.LOGIN_USER+token);
        }
        return loginUser;
    }
}
