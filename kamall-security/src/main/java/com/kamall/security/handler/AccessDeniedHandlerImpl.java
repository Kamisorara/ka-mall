package com.kamall.security.handler;

import com.alibaba.fastjson.JSON;
import com.kamall.common.api.CommonResult;
import com.kamall.common.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户权限异常处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");
        CommonResult result = CommonResult.validateFailed("权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
