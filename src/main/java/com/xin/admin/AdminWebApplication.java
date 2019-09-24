package com.xin.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 类功能描述: AdminWebApplication启动类
 *
 * @author Eternal
 * @date 2019/5/12 0:10
 */

@SpringBootApplication
@MapperScan("com.xin.admin.sys.mapper")
public class AdminWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminWebApplication.class, args);
	}

}
