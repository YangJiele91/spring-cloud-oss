package com.oss.gateway.filter;

import com.oss.common.model.LoginUser;
import com.oss.common.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class AccessFilter implements GlobalFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${notFilterUri}")
    private String[] notFilterUri;
    @Value("${loginExpireTime}")
    private Long loginExpireTime;
    @Value("${isLogin}")
    private boolean isLogin;

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (this.shouldFilter(request) && this.isLogin) {
            ServerHttpResponse response = exchange.getResponse();
            String accessToken = request.getHeaders().getFirst("Authorization");
            if (StringUtils.isBlank(accessToken)) {
                DataBuffer buffer = response.bufferFactory().wrap("Authorization token is empty".getBytes(StandardCharsets.UTF_8));
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.writeWith(Mono.just(buffer));
            }

            LoginUser loginUser = (LoginUser)this.redisUtil.get("login_user" + accessToken);
            if (loginUser == null) {
                DataBuffer buffer = response.bufferFactory().wrap("Authorization token is expire".getBytes(StandardCharsets.UTF_8));
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.writeWith(Mono.just(buffer));
            }

            this.redisUtil.expire("login_user" + accessToken, this.loginExpireTime);
        }

        return chain.filter(exchange);
    }

    private boolean shouldFilter(ServerHttpRequest request) {
        boolean result = true;
        for (String uri : this.notFilterUri) {
            if (request.getURI().getPath().contains(uri)) {
                result = false;
            }
        }
        return result;
    }
}