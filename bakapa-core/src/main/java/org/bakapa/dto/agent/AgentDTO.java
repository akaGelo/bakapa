package org.bakapa.dto.agent;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "agentId")
public class AgentDTO {

    @NotNull
    @NotEmpty
    private String agentId;

    private LocalDateTime createDate;

    @Nullable
    private String note;

    public static AgentDTO demo(String agentId) {
        return new AgentDTO(agentId, LocalDateTime.now(),"note");
    }
}
