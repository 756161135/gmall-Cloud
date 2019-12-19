package com.atguigu.gmall.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient//开启注册中心客户端
@EnableFeignClients //开启远程调用
public class GmallItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmallItemApplication.class, args);
	}

}
