package com.school.reyfow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class PublishNotificationService {

    @Inject
    SnsClient snsClient;

    @ConfigProperty(name = "aws.topic.arn")
    String topic;

    private static final Logger log = LoggerFactory.getLogger(PublishNotificationService.class);

    public void publish(final Object notification, final Map<String, MessageAttributeValue> headers) {
        log.info("Publicando notificação para o topico:{}", topic);
        snsClient.publish(sns -> sns
                .message(convertNotification(notification))
                .topicArn(topic)
                .messageAttributes(headers)
                .build());
    }

    private String convertNotification(Object notification) {
        final var mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter a notificação");
        }
    }
}
