package com.demis.willy.skills.soa.service.impl;

import com.demis.willy.skills.soa.notifications.CalcNotification;
import com.demis.willy.skills.soa.service.CalcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalcServiceImpl implements CalcService {
    private static Logger logger = LoggerFactory.getLogger(CalcServiceImpl.class);

    @Override
    public double processNotification(CalcNotification notification) {
        return switch(notification.getNotificationType()) {
            case ADD -> add(notification.getOperandLeft(), notification.getOperandRight());
            case SUBTRACT -> subtract(notification.getOperandLeft(), notification.getOperandRight());
            case MULTIPLY -> multiply(notification.getOperandLeft(), notification.getOperandRight());
            case DIVIDE -> divide(notification.getOperandLeft(), notification.getOperandRight());
            default -> throw new IllegalStateException();
        };
    }

    private double add(double left, double right) {
        logger.info("Performing addition of {} + {}", left, right);
        return left + right;
    }

    public double subtract(double left, double right) {
        logger.info("Performing subtraction of {} - {}", left, right);
        return left - right;
    }

    public double multiply(double left, double right) {
        logger.info("Performing multiplication of {} x {}", left, right);
        return left * right;
    }

    public double divide(double left, double right) {
        logger.info("Performing division of {} / {}", left, right);
        return left / right;
    }
}
