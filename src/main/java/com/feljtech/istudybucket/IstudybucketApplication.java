package com.feljtech.istudybucket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kanye - Elroy Kanye
 */
@SpringBootApplication
@EnableAsync
public class IstudybucketApplication {

    public static void main(String[] args) {
        SpringApplication.run(IstudybucketApplication.class, args);
    }

}
