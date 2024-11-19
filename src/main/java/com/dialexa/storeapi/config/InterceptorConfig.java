package com.dialexa.storeapi.config;

import com.dialexa.storeapi.entities.interfaces.CustomInterceptor;
import java.util.Arrays;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;

    public InterceptorConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Use the ApplicationContext to find all beans of type CustomInterceptor
        String[] interceptorBeans = applicationContext.getBeanNamesForType(CustomInterceptor.class);

        Arrays.stream(interceptorBeans).forEach(beanName -> {
            CustomInterceptor customInterceptor = (CustomInterceptor) applicationContext.getBean(beanName);
            registry.addInterceptor(customInterceptor);
        });
    }
}
