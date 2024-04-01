package com.redislearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisLearnApplication.class, args);
	}

}
