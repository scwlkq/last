package com.iweb.sp.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author Zxy
 * @date 2022/8/15 16:43
 * @description
 */
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
//这个和下面做的一样，不过只需要告诉它配置类就可以了
    //下面是根，这个是简化
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
//        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/test"};
    }

    //乱码处理
    @Override
    protected Filter[] getServletFilters() {
        //相当于过滤器
        CharacterEncodingFilter filter=new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};
    }
}

//public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer{
//
//    //加载springMvc对应的容器对象
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
//        ctx.register(SpringMvcConfig.class);
//        return ctx;
//    }
//
//    //那些请求归springMvc处理
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    //加载spring配置对应的容器对象
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
//        ctx.register(SpringConfig.class);
//        return ctx;
//    }
//}
