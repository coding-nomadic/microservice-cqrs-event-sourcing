package com.bank.account.command.producer;

import com.bank.account.command.configuration.ConfigProperties;
import com.bank.account.command.event.DepositFundAccountEvent;
import com.bank.account.command.exception.OpenAccountProcessingException;
import com.bank.account.command.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DepositFundAccountProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ConfigProperties configProperties;

    @Autowired
    public DepositFundAccountProducer(KafkaTemplate<String, String> kafkaTemplate, ConfigProperties configProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.configProperties = configProperties;
    }

    /**
     * Publishes message to Kafka topic.
     **/
    public void publish(DepositFundAccountEvent depositFundAccountEvent) {
        try {
            String jsonEvent = JsonUtils.toString(depositFundAccountEvent);
            kafkaTemplate.send(configProperties.getDepositFundTopic(), jsonEvent);
            log.info("Message sent successfully to topic: {}, Event: {}", configProperties.getDepositFundTopic(), jsonEvent);
        } catch (Exception e) {
            log.error("Error occurred while converting event to JSON: {}", e.getMessage());
            throw new OpenAccountProcessingException("Error occurred while publishing message to Kafka", "102");
        }
    }
}
