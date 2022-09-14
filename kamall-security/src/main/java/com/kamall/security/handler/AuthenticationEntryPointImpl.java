package com.kamall.security.handler;

import com.alibaba.fastjson.JSON;
import com.kamall.common.api.CommonResult;
import com.kamall.common.util.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户权限异常处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        ResponseResult result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), );
        CommonResult result = CommonResult.validateFailed("认证失败，无法访问服务器资源，请重试！");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}

