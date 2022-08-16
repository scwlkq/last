package com.iweb.sp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Zxy
 * @date 2022/8/15 16:39
 * @description spring的配置
 */
@Configuration
@ComponentScan("com.iweb.sp.controller")
@EnableWebMvc //由JSON转换成对象
public class SpringMvcConfig {
}
