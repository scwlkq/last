package com.iweb.sp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lukecheng
 * @date 2022/08/13
 */


@Configuration  //配置类
@ComponentScan("com.iweb.sp.dao")
public class MpConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        //1.定义mp的拦截器
        //2.添加具体的拦截器
        MybatisPlusInterceptor mpInterceptor = new MybatisPlusInterceptor();  //mp的拦截器
        mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());   //分页拦截器
        return mpInterceptor;
    }
}
