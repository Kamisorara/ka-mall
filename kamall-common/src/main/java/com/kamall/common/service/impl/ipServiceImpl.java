package com.kamall.common.service.impl;

import com.kamall.common.service.ipService;
import com.kamall.common.util.RequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class ipServiceImpl implements ipService {
    @Override
    public String getUserInquireIpAddress(ServletRequestAttributes servletRequestAttributes) {
        //查询ip地址
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return RequestUtil.getRequestIp(request);
    }
}
