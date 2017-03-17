package com.bjzkar.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 用@EnableZuulProxy注解激活Zuul
 * @author captainemo
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGatewayApplication.class, args);
	}
}
