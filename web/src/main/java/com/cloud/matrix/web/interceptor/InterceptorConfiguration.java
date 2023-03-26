package com.cloud.matrix.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author michael
 * @version $Id: InterceptorConfiguration.java, v 0.1 2023-03-07 3:13 PM Michael Exp $$
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private TenantInterceptor tenantInterceptor;

    @Autowired
    private LoginInterceptor  loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 2. 租户拦截器，设置用户请求的租户，返回true
        registry.addInterceptor(tenantInterceptor).addPathPatterns("/**").excludePathPatterns("/",
            "/index", "/index.htm", "/index.hml", "/css/**", "/js/**",
            "/gateway/access/register.json", "/gateway/access/login.json", "/gateway/account/testList.json");
        // 3. auth拦截器，检查用户是否登录，返回true or false
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(
            "/gateway/login.json", "/css/**", "/js/**", "/gateway/access/register.json",
            "/gateway/access/login.json", "/gateway/account/testList.json");
    }
}
