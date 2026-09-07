package com.clearflow.settlement;

import org.springframework.boot.SpringApplication;

public class TestClearflowSettlementApplication {

	public static void main(String[] args) {
		SpringApplication.from(ClearflowSettlementApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
