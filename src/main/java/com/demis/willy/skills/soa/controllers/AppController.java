package com.demis.willy.skills.soa.controllers;

import com.demis.willy.skills.soa.notifications.CalcNotification;
import com.demis.willy.skills.soa.notifications.NotificationType;
import com.demis.willy.skills.soa.service.QueueingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/calc")
public class AppController {
    private static Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private QueueingService queueingService;

    @RequestMapping(value="/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void performAdd(@RequestBody CalcNotification notification) {
        logger.info("Received an add request for notification: {}", notification);
        notification.setNotificationType(NotificationType.ADD);

        queueingService.queueNotification(notification);
    }

    @RequestMapping(value="/subtract", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void performSubtract(@RequestBody CalcNotification notification) {
        logger.info("Received a subtract request for notification: {}", notification);
        notification.setNotificationType(NotificationType.SUBTRACT);

        queueingService.queueNotification(notification);
    }

    @RequestMapping(value="/multiply", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void performMultiply(@RequestBody CalcNotification notification) {
        logger.info("Received a multiply request for notification: {}", notification);
        notification.setNotificationType(NotificationType.MULTIPLY);

        queueingService.queueNotification(notification);
    }

    @RequestMapping(value="/divide", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void performDivide(@RequestBody CalcNotification notification) {
        logger.info("Received a divide request for notification: {}", notification);
        notification.setNotificationType(NotificationType.DIVIDE);

        queueingService.queueNotification(notification);
    }
}
