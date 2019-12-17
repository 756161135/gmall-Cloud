package com.atguigu.gmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient //开启注册中心
@MapperScan(basePackages = "com.atguigu.gmall.manage.mapper")
public class GmallManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmallManageApplication.class, args);
	}

}
