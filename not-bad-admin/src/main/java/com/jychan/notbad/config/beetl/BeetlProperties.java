package com.jychan.notbad.config.beetl;

import org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhangtao on 2016-12-01.
 */
@ConfigurationProperties(prefix = "spring.beetl")
public class BeetlProperties extends AbstractTemplateViewResolverProperties {

    public static final String DEFAULT_RESOURCE_LOADER_PATH = "classpath:/templates/";

    public static final String DEFAULT_PROPS_PATH = "classpath:/beetl.properties";

    public static final String DEFAULT_PREFIX = "";

    public static final String DEFAULT_SUFFIX = ".html";

    /**
     * Template path.
     */
    private String resourceLoaderPath = DEFAULT_RESOURCE_LOADER_PATH;

    private String propsPath = DEFAULT_PROPS_PATH;

    public BeetlProperties() {
        super(DEFAULT_PREFIX, DEFAULT_SUFFIX);
    }

    public String getResourceLoaderPath() {
        return resourceLoaderPath;
    }

    public void setResourceLoaderPath(String resourceLoaderPath) {
        this.resourceLoaderPath = resourceLoaderPath;
    }

    public String getPropsPath() {
        return propsPath;
    }

    public void setPropsPath(String propsPath) {
        this.propsPath = propsPath;
    }
}
