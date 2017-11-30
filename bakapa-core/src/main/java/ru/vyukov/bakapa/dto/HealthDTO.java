package ru.vyukov.bakapa.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.jakewharton.byteunits.BinaryByteUnit;
import lombok.*;
import ru.vyukov.bakapa.domain.AgentStatus;

import javax.validation.constraints.NotNull;
import java.util.Map;

import static com.jakewharton.byteunits.BinaryByteUnit.GIBIBYTES;

@Value
@Builder
public class HealthDTO {

    public static final String DESTINATION = "/health";

    @NonNull
    private Double sla;

    @NonNull
    private Long freeDiskSpaceBytes;


    public static HealthDTO demo() {
        return builder()
                .sla(1.3)
                .freeDiskSpaceBytes(GIBIBYTES.toBytes(1))
                .build();
    }
}
