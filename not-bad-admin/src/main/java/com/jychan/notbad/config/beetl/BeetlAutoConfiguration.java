package com.jychan.notbad.config.beetl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.template.TemplateLocation;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by zhangtao on 2016-12-01.
 */
@Configuration
@ConditionalOnClass({GroupTemplate.class, BeetlSpringViewResolver.class})
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(BeetlProperties.class)
public class BeetlAutoConfiguration {

    private static final Log logger = LogFactory.getLog(BeetlAutoConfiguration.class);

    private final ApplicationContext applicationContext;

    private final BeetlProperties beetlProperties;

    public BeetlAutoConfiguration(ApplicationContext applicationContext, BeetlProperties beetlProperties) {
        this.applicationContext = applicationContext;
        this.beetlProperties = beetlProperties;
    }

    @PostConstruct
    public void checkTemplateLocationExists() {
        if (beetlProperties.isCheckTemplateLocation()) {
            logger.info("rooot path = " + BeetlAutoConfiguration.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            TemplateLocation location = new TemplateLocation(beetlProperties.getResourceLoaderPath());
            if (!location.exists(this.applicationContext)) {
                logger.warn("Cannot find template location: " + location);
            }
        }
    }

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
            // WebAppResourceLoader 配置root路径是关键
            String path = patternResolver.getResource(beetlProperties.getResourceLoaderPath()).getFile().getPath();
            logger.info("WebAppResourceLoader path: " + path);
            WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(path);
            beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
        } catch (IOException e) {
            logger.warn("Cannot find template location: " + beetlProperties.getResourceLoaderPath());
        }

        //读取配置文件信息
        beetlGroupUtilConfiguration.setConfigFileResource(
                patternResolver.getResource(beetlProperties.getPropsPath()));
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setSuffix(beetlProperties.getSuffix());
//        beetlSpringViewResolver.setPrefix("");
//        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE - 5);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }
}
