package com.demis.willy.skills.soa.notifications;

import com.demis.willy.skills.soa.notifications.deserializers.NotificationLocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.aspectj.weaver.ast.Not;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@JsonDeserialize(builder = CalcNotification.Builder.class)
public class CalcNotification implements Serializable {

    @JsonProperty("operand_left")
    private final double operandLeft;

    @JsonProperty("operand_right")
    private final double operandRight;

    @JsonProperty("requested_at")
    private final Date requestedAt;

    @JsonProperty("notification_type")
    private NotificationType notificationType;

    public double getOperandLeft() { return operandLeft; }
    public double getOperandRight() { return operandRight; }
    public Date getRequestedAt() { return requestedAt; }
    public NotificationType getNotificationType() { return notificationType; }
    public CalcNotification setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    private CalcNotification(Builder builder) {
        this.operandLeft = builder.operandLeft;
        this.operandRight = builder.operandRight;
        this.requestedAt = builder.requestedAt;
        this.notificationType = builder.notificationType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Calc Notification: ").append("\n");
        sb.append("\t").append(" operandLeft: ").append(operandLeft).append("\n");
        sb.append("\t").append(" operandRight: ").append(operandRight).append("\n");
        sb.append("\t").append(" requestedAt: ").append(requestedAt).append("\n");
        return sb.toString();
    }

    public static class Builder {
        private double operandLeft;
        private double operandRight;
        private Date requestedAt;
        private NotificationType notificationType;

        public Builder() {
        }

        @JsonCreator
        public Builder(@JsonProperty("operand_left") double operandLeft,
                       @JsonProperty("operand_right") double operandRight,
                       @JsonProperty("requested_at")
                       @JsonDeserialize(using = NotificationLocalDateDeserializer.class) Date requestedAt,
                       @JsonProperty("notification_type") NotificationType type) {
            this.operandLeft = operandLeft;
            this.operandRight = operandRight;
            this.requestedAt = requestedAt;
            this.notificationType = type;
        }

        public Builder withNotificationType(NotificationType type) {
            this.notificationType = type;
            return this;
        }

        public CalcNotification build() {
            if (requestedAt == null) {
                ZonedDateTime zonedDateTimeInEST = ZonedDateTime.now(ZoneId.of("America/Indiana/Indianapolis"));
                requestedAt = Date.from(zonedDateTimeInEST.toInstant());
            }
            return new CalcNotification(this);
        }
    }

}
