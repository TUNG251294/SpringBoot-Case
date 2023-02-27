package com.casestore.configuration;

//import com.casestore.formatter.CustomerFormatter;
//import com.casestore.formatter.ProductFormatter;
import com.casestore.service.impl.CustomerService;
//import com.casestore.service.impl.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableTransactionManagement
public class AppConfig implements ApplicationContextAware, WebMvcConfigurer {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new ProductFormatter(applicationContext.getBean(ProductService.class)));
//        registry.addFormatter(new CustomerFormatter(applicationContext.getBean(CustomerService.class)));
//    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
