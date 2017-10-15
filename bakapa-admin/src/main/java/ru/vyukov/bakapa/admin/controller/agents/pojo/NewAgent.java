package ru.vyukov.bakapa.admin.controller.agents.pojo;

import lombok.Data;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class NewAgent {

    @NotNull
    @NotEmpty
    private String agentId;
}
