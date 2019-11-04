package com.koreabaseball.koreabaseball;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class KoreabaseballApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoreabaseballApplication.class, args);
    }

}
