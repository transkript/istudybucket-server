package com.elroykanye.istudybucket;

import com.elroykanye.istudybucket.config.global.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kanye - Elroy Kanye
 */
@EnableAsync
@SpringBootApplication
@Import(SwaggerConfig.class)
public class IstudybucketApplication {

    public static void main(String[] args) {
        SpringApplication.run(IstudybucketApplication.class, args);
    }

}
