package com.bank.account.command.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bank")
@Data
public class ConfigProperties {
    private String openAccountTopic;
    private String depositFundTopic;
    private String closeAccountTopic;
    private String withdrawFundTopic;
}
