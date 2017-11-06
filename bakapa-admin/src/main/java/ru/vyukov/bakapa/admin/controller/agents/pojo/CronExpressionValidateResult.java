package ru.vyukov.bakapa.admin.controller.agents.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CronExpressionValidateResult {


    private boolean wrong;

    private final Instant now = Instant.now();

    private Instant nextExecution;


    public static CronExpressionValidateResult wrongExpression(String expression) {
        return new CronExpressionValidateResult(true, null);
    }

    public static CronExpressionValidateResult validExpression(String expression, Date nextDate) {
        Instant nextInstant = nextDate.toInstant();
        return new CronExpressionValidateResult(false, nextInstant);
    }
}
