package com.hmm.hmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HmmApplication {
    public static void main(String[] args) {
        SpringApplication.run(HmmApplication.class, args);
    }

}
