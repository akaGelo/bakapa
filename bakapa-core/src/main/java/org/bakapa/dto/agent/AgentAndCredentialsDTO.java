package org.bakapa.dto.agent;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.beans.ConstructorProperties;
import java.time.Instant;

/**
 * Agent and credentials
 */

//TODO refactoring to aggregation see AbstractAgentAndInfoDTO
@Data
@EqualsAndHashCode(callSuper = true)
public class AgentAndCredentialsDTO extends  AgentDTO{

    private String password;

    @ConstructorProperties({"agentId","createDate","note","password"})
    public AgentAndCredentialsDTO(String agentId, Instant createDate, String note, String password) {
        super(agentId, createDate, note);
        this.password = password;
    }

    public static AgentAndCredentialsDTO demo(String idAndPass) {
        return new AgentAndCredentialsDTO(idAndPass,Instant.now(),"note",idAndPass);
    }
}
