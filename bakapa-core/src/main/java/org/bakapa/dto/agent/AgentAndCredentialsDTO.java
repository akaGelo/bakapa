package org.bakapa.dto.agent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Agent and credentials
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AgentAndCredentialsDTO extends  AgentDTO{

    private String password;

    @ConstructorProperties({"agentId","createDate","note","password"})
    public AgentAndCredentialsDTO(String agentId, ZonedDateTime createDate, String note, String password) {
        super(agentId, createDate, note);
        this.password = password;
    }

    public static AgentAndCredentialsDTO demo(String idAndPass) {
        return new AgentAndCredentialsDTO(idAndPass,ZonedDateTime.now(),"note",idAndPass);
    }
}
