package com.demis.willy.skills.soa.service.impl;

import com.demis.willy.skills.soa.notifications.CalcNotification;
import com.demis.willy.skills.soa.service.CalcService;
import com.demis.willy.skills.soa.service.QueueingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueingServiceImpl implements QueueingService {
    private static final Logger logger = LoggerFactory.getLogger(QueueingServiceImpl.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private CalcService calcService;

    @Value("${amq.CalcNotificationQueue}")
    private String calcNotificationQueue;



    @Override
    public void queueNotification(CalcNotification notification) {
        try {
            logger.info("Queuing notification: {}", notification);
            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(notification);
            jmsTemplate.send(calcNotificationQueue, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(jsonObj);
                return message;
            });
        }
        catch (Exception ex) {
            logger.error("ERROR in sending message to queue", ex);
        }
    }

    @JmsListener(destination = "${amq.CalcNotificationQueue}")
    public void dequeueNotification(final Message textNotification) throws JMSException, JsonProcessingException {
        if(textNotification instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)textNotification;
            String jsonNotificationString = textMessage.getText();
            CalcNotification calcNotification = new ObjectMapper().readValue(jsonNotificationString, CalcNotification.class);

            logger.info("Dequeued notification: {} for processing", calcNotification);
            calcService.processNotification(calcNotification);
        }
    }
}
