package ru.vyukov.bakapa.controller.domain.agent;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;

import static com.jakewharton.byteunits.BinaryByteUnit.GIBIBYTES;

@Value
@Builder
public class Health {

    public static final String DESTINATION = "/health";

    @NonNull
    @JsonView(Summary.class)
    private Double sla;

    @NonNull
    @JsonView(Summary.class)
    private Long freeDiskSpaceBytes;


    public static Health demo() {
        return builder()
                .sla(1.3)
                .freeDiskSpaceBytes(GIBIBYTES.toBytes(1))
                .build();
    }
}
