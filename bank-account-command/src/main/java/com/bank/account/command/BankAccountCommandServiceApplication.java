package com.bank.account.command;

import com.bank.account.command.configuration.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class BankAccountCommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountCommandServiceApplication.class, args);
	}
}
