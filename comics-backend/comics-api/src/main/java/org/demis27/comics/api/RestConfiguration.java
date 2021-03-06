package org.demis27.comics.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "org.demis27.comics.api",
        "org.demis27.comics.business",
        "org.demis27.comics.data"})
@PropertySource(value = {"classpath:comics.properties"})
@Component
public class RestConfiguration extends WebMvcConfigurationSupport {

    public RestConfiguration() {
        super();
        List<HandlerExceptionResolver> resolvers = new ArrayList<>();
        resolvers.add(new HandlerException400Resolver());
        configureHandlerExceptionResolvers(resolvers);
    }

    public static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    private Environment environment;

    public int getDefaultPageSize() {
        Integer defaultPageSize = environment.getProperty("rest.controller.default.page.size", Integer.class);
        return defaultPageSize != null ? defaultPageSize : DEFAULT_PAGE_SIZE;
    }

}
