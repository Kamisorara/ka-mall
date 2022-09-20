package com.kamall.common.service;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 获取ip地址相关service
 */
public interface ipService {
    //获取当前用户访问ip地址
    String getUserInquireIpAddress(ServletRequestAttributes attributes);

}
