package org.bakapa.dto.agent;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

/**
 * Agent and credentials
 */

@Data
public class AgentAndCredentialsDTO extends  AgentDTO{

    private String password;

    @ConstructorProperties({"agentId","createDate","note","password"})
    public AgentAndCredentialsDTO(String agentId, LocalDateTime createDate, String note, String password) {
        super(agentId, createDate, note);
        this.password = password;
    }

    public static AgentAndCredentialsDTO demo(String idAndPass) {
        return new AgentAndCredentialsDTO(idAndPass,LocalDateTime.now(),"note",idAndPass);
    }
}
