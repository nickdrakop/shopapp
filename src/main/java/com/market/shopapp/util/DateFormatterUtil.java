/**
 * @author nick.drakopoulos
 */

package com.market.shopapp.util;

import com.market.shopapp.exception.AppError;
import com.market.shopapp.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormatterUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DateFormatterUtil.class);

    private final ZoneId zoneId;

    private final String dateFormat;

    @Autowired
    public DateFormatterUtil(@Value("${date.timezone.id}") String timezoneId,
                             @Value("${date.format.timestamp}") String dateFormat) {
        this.zoneId = ZoneId.of(timezoneId);
        this.dateFormat = dateFormat;
    }

    public Instant getInstant(String nonNullableDate) {
        LocalDateTime localDateTime;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            localDateTime = LocalDateTime.from(formatter.parse(nonNullableDate));

        } catch (IllegalArgumentException e) {
            LOG.error(String.format("Error when tried to parse date parameter: %s", nonNullableDate), e);
            throw new ApplicationException(AppError.INVALID_DATE_FORMAT);
        }

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        return Instant.from(zonedDateTime);
    }

    public String instantToStringTimezonedDate(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat).withZone(zoneId);
        return formatter.format(instant);
    }
}
