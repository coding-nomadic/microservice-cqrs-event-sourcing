package com.bank.account.command.producer;

import com.bank.account.command.configuration.ConfigProperties;
import com.bank.account.command.event.OpenBankAccountEvent;
import com.bank.account.command.exception.OpenAccountProcessingException;
import com.bank.account.command.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OpenBankAccountProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ConfigProperties configProperties;

    @Autowired
    public OpenBankAccountProducer(KafkaTemplate<String, String> kafkaTemplate, ConfigProperties configProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.configProperties = configProperties;
    }

    /**
     * Publishes message to Kafka topic.
     **/
    public void publish(OpenBankAccountEvent openBankAccountEvent) {
        try {
            String jsonEvent = JsonUtils.toString(openBankAccountEvent);
            kafkaTemplate.send(configProperties.getOpenAccountTopic(), jsonEvent);
            log.info("Message sent successfully to topic: {}, Event: {}", configProperties.getOpenAccountTopic(), jsonEvent);
        } catch (Exception e) {
            log.error("Error occurred while converting event to JSON: {}", e.getMessage());
            throw new OpenAccountProcessingException("Error occurred while publishing message to Kafka", "102");
        }
    }
}
