package com.jdann;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAsync
@SpringBootApplication
public class ImageApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(ImageApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }
}
