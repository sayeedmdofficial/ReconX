package com.clearflow.settlement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class ClearflowSettlementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClearflowSettlementApplication.class, args);
	}

}
