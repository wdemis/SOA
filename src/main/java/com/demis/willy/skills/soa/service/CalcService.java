package com.demis.willy.skills.soa.service;

import com.demis.willy.skills.soa.notifications.CalcNotification;

public interface CalcService {
    double processNotification(CalcNotification notification);
}
