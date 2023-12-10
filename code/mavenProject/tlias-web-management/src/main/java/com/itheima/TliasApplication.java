package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan		// 开启Servlet组件，才可以用WebFilter
@SpringBootApplication
public class TliasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TliasApplication.class, args);
	}

}
