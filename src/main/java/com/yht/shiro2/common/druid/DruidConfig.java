package com.yht.shiro2.common.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid 配置
 */
@Configuration
public class DruidConfig {

    /**
     * 绑定数据源配置
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     * 配置druid监控
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        Map<String,String> initParam = new HashMap<>();
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME,"root");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD,"123");
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW,"");
        initParam.put(StatViewServlet.PARAM_NAME_DENY,"192.168.1.22");

        bean.setInitParameters(initParam);
        return bean;
    }

    @Bean
    public FilterRegistrationBean filter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParam = new HashMap<>();
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
