package com.ranyk.studyspringsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ClassName:SecurityConfig
 * <p>
 * Description:安全配置类
 *
 * @author ranyi
 * @date 2020-04-10 9:21
 * Version: V1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 对Security的授权配置
     *
     * @param http HttpSecurity对象
     * @throws Exception 抛出异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //对Security的访问请求的配置，等同于SpringMVC的请求拦截器的配置
        //授权请求设置
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //启用登录模式 设置没有登录，跳转到登录页面
        http.formLogin()
                //设置登录页面的跳转请求
                .loginPage("/toLogin")
                //设置前台传入后台的账户信息参数，和前台的组件的name属性绑定
                .usernameParameter("user")
                .passwordParameter("pwd")
                //设置 登录处理网址
                .loginProcessingUrl("/login");


        //禁用csrf功能，防止跨站请求伪造(Cross-site request forgery) 防止通过GET方式的请求
        http.csrf().disable();
        //启用注销模式 设置没有注销，跳转到登录页面
        http.logout().logoutSuccessUrl("/");

        //开启账户保存功能 利用本地浏览器的cookie保存账户信息，默认保存时间为14(两周)天
        http.rememberMe().rememberMeParameter("remember");


    }


    /**
     * 对Security的认证配置
     *
     * @param auth AuthenticationManagerBuilder建造者对象
     * @throws Exception 抛出异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //从内存中读取有关账户的信息，包括用户名，密码，以及角色
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("ranyk").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
