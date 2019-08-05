package com.bixbysdoghouse.training.spring.springmessageconvertersample;

import com.bixbysdoghouse.training.spring.springmessageconvertersample.web.DatasetConverter;
import org.apache.jena.query.Dataset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class SpringConfiguration {
    @Bean
    public HttpMessageConverter<Dataset> createXmlHttpMessageConverter() {
        return new DatasetConverter();
    }

}
