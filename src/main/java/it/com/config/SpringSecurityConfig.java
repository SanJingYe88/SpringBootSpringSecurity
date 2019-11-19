package it.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Autowired
    private MyUserDetailService userDetailService;
    @Autowired
    private MyLogOutSuccessHandler logOutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)    // 添加验证码校验过滤器
                .formLogin()
                .loginPage("/mustAuth")       //指定跳转登录url
                .loginProcessingUrl("/login")   //指定登录请求
                .successHandler(authenticationSuccessHandler) // 处理登录成功
                .failureHandler(authenticationFailureHandler) // 处理登录失败
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                .tokenValiditySeconds(3600) // remember 过期时间，单为秒
                .userDetailsService(userDetailService) // 处理自动登录逻辑
                .and()
                .logout()
                .logoutUrl("/signout")
//                .logoutSuccessUrl("/signout/success")
                .logoutSuccessHandler(logOutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .authorizeRequests()
                .antMatchers("/login.html","/code/image","/mustAuth","/css/**").permitAll()     //表示跳转到登录页面的请求不被拦截, 配置css样式不被拦截
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }
}
