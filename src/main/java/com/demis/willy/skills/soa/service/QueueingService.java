package com.demis.willy.skills.soa.service;

import com.demis.willy.skills.soa.notifications.CalcNotification;

public interface QueueingService {
    void queueNotification(CalcNotification notification);
}
