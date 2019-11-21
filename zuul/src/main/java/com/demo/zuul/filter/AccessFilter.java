package com.demo.zuul.filter;

import com.demo.constant.RedisKey;
import com.demo.model.LoginUser;
import com.demo.util.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * 资源过滤器
 * 所有的资源请求在路由之前进行前置过滤
 * 如果请求头不包含 Authorization参数值，直接拦截不再路由
 */
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Autowired
    RedisUtil redisUtil;

    @Value("${notFilterUri}")
    private String[] notFilterUri;
    @Value("${loginExpireTime}")
    private Long loginExpireTime;
    @Value("${isLogin}")
    private boolean isLogin;

    /**
     * 过滤器的类型 pre表示请求在路由之前被过滤
     * @return 类型
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序
     * @return 顺序 数字越大表示优先级越低，越后执行
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否会被执行
     * @return true
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        // 登陆白名单
        boolean result = true;
        for (String uri : notFilterUri) {
            if (request.getRequestURI().contains(uri)) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 系统登陆
     * @return 过滤结果
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String accessToken = request.getHeader("Authorization");

        if (StringUtils.isBlank(accessToken)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            requestContext.setResponseBody("Authorization token is empty");
            return null;
        }

        // 登录Token校验
        LoginUser loginUser = redisUtil.get(RedisKey.LOGIN_USER + accessToken);
        if(loginUser == null && isLogin){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            requestContext.setResponseBody("Authorization token is expire");
            return null;
        }

        redisUtil.expire(RedisKey.LOGIN_USER + accessToken, loginExpireTime);
        return null;
    }
}
