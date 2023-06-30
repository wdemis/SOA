package com.demis.willy.skills.soa.notifications.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class NotificationLocalDateDeserializer extends StdDeserializer<Date> {

    public NotificationLocalDateDeserializer() {
        this(null);
    }

    protected NotificationLocalDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String timestamp = jsonParser.getText();

        /* Convert UTC timestamps to local time.
            Note that API can support:   yyyy-MM-dd HH:mm:ss UTC
            and can also support:        yyyy-MM-dd'T'HH:mm:ss'Z'
            and can also support:        1688036342000

            The date formatter below handles both as long as the 'UTC' is removed from the former
            since it is an invalid format
        */


        if (timestamp.matches("\\d+")) {
            //Just a epoch unix timestamp
            return new Date(Long.parseLong(timestamp));

        } else {
            if (timestamp.endsWith("UTC")) {
                timestamp = timestamp.substring(0, timestamp.indexOf("UTC")).trim();
            }

            DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd[ ]['T']HH:mm:ss['Z']").withZone(ZoneId.of("UTC").normalized());
            ZonedDateTime zonedDateTimeInUTC = ZonedDateTime.parse(timestamp, DATE_TIME_FORMATTER);
            ZonedDateTime zonedDateTimeInEST = zonedDateTimeInUTC.withZoneSameInstant(ZoneId.of("America/Indiana/Indianapolis"));
            Date localDate = Date.from(zonedDateTimeInEST.toInstant());
            return localDate;
        }
    }
}
