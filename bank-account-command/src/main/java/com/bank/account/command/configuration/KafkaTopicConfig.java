package com.bank.account.command.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value("${bank.openAccountTopic}")
    private String openAccountTopic;

    @Value("${bank.depositFundTopic}")
    private String depositFundTopic;

    @Value("${bank.closeAccountTopic}")
    private String closeAccountTopic;

    @Value("${bank.withdrawFundTopic}")
    private String withdrawFundTopic;

    /**
     * Creates a new topic for opening bank accounts.
     */
    @Bean
    public NewTopic openAccountTopic() {
        return TopicBuilder.name(openAccountTopic).partitions(3).build();
    }

    /**
     * Creates a new topic for depositing funds into bank accounts.
     */
    @Bean
    public NewTopic depositFundTopic() {
        return TopicBuilder.name(depositFundTopic).partitions(3).build();
    }

    /**
     * Creates a new topic for closing bank accounts.
     */
    @Bean
    public NewTopic closeAccountTopic() {
        return TopicBuilder.name(closeAccountTopic).partitions(3).build();
    }

    /**
     * Creates a new topic for withdrawing funds from bank accounts.
     */
    @Bean
    public NewTopic withdrawFundTopic() {
        return TopicBuilder.name(withdrawFundTopic).partitions(3).build();
    }
}
