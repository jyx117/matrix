package com.cloud.matrix.start.interceptor;

import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 租户拦截器
 * @author michael
 * @version $Id: TenantInterceptor.java, v 0.1 2023-03-03 5:44 PM Michael Exp $$
 */
@Component
public class TenantInterceptor implements HandlerInterceptor {

    private static final String TENANT_HEADER_KEY = "tenant-header";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        // 从header中获取，如果有TENANT_HEADER_KEY就按这个设置
        String tenant = request.getHeader(TENANT_HEADER_KEY);
        if (StringUtil.isNotBlank(tenant)) {
            CoreContext.setTenant(tenant);
            return true;
        }
        // 从param中获取
        tenant = request.getParameter("tenant");
        if (StringUtil.isNotBlank(tenant)) {
            CoreContext.setTenant(tenant);
            return true;
        }

        CoreContext.setTenant(null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        CoreContext.cleanTenantContext();
    }
}
