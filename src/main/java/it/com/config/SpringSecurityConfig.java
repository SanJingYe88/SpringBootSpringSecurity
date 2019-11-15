package it.com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")       //指定登录页面
                .loginProcessingUrl("/login")   //指定登录请求
                .and()
                .authorizeRequests()
                .antMatchers("/login.html","/css/**").permitAll()     //表示跳转到登录页面的请求不被拦截, 配置css样式不被拦截
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }
}
