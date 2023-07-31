package com.github.zhangguoqing.service.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 对请求进行鉴权的配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                // 没有权限进入内置的登录页面
                .formLogin()
                .and()
                // 暂时关闭CSRF校验，允许get请求登出
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用userDetailsService进行认证
        auth.userDetailsService(null);
    }
}
