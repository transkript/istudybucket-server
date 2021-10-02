package com.feljtech.istudybucket;

import com.feljtech.istudybucket.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kanye - Elroy Kanye
 */
@SpringBootApplication
@EnableAsync
@Import(SwaggerConfig.class)
public class IstudybucketApplication {

    public static void main(String[] args) {
        SpringApplication.run(IstudybucketApplication.class, args);
    }

}
