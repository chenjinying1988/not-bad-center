package com.jychan.notbad.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by chenjinying on 2017/5/7.
 * mail: 415683089@qq.com
 */
@Configuration
public class DatabaseConfig {

    private static final String DATASOURCE_PREFIX_1 = "notbad.durid.datasource";

    @Primary
    @Bean(initMethod = "init",destroyMethod = "close")
    @ConfigurationProperties(prefix = DATASOURCE_PREFIX_1)
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setProxyFilters(Arrays.<Filter>asList(statFilter()));
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = DATASOURCE_PREFIX_1)
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setMergeSql(true);
        statFilter.setLogSlowSql(true);
        statFilter.setSlowSqlMillis(500);
        return statFilter;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("config.message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }
}
