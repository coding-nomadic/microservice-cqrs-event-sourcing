package com.bank.account.command.producer;

import com.bank.account.command.configuration.ConfigProperties;
import com.bank.account.command.event.CloseBankAccountEvent;
import com.bank.account.command.exception.OpenAccountProcessingException;
import com.bank.account.command.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteBankAccountProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ConfigProperties configProperties;

    @Autowired
    public DeleteBankAccountProducer(KafkaTemplate<String, String> kafkaTemplate, ConfigProperties configProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.configProperties = configProperties;
    }

    /**
     * Publishes message to Kafka topic.
     **/
    public void publish(CloseBankAccountEvent closeBankAccountEvent) {
        try {
            String jsonEvent = JsonUtils.toString(closeBankAccountEvent);
            kafkaTemplate.send(configProperties.getCloseAccountTopic(), jsonEvent);
            log.info("Message sent successfully to topic: {}, Event: {}", configProperties.getCloseAccountTopic(), jsonEvent);
        } catch (Exception e) {
            log.error("Error occurred while converting event to JSON: {}", e.getMessage());
            throw new OpenAccountProcessingException("Error occurred while publishing message to Kafka", "102");
        }
    }
}
