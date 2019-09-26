package com.stackroute.userloginservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class UserLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLoginServiceApplication.class, args);
	}

}
