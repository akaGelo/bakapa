package ru.vyukov.bakapa.controller.domain;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import ru.vyukov.bakapa.controller.domain.View.Full;


@EqualsAndHashCode(of = "id",callSuper = false)
public class DetectionConfig extends RuntimeConfig {

    public static final String INSTANCE_ID = "detectionConfig";


    @NotNull
    @JsonView(Full.class)
    private Set<Integer> mongoPorts;

    @NotNull
    @JsonView(Full.class)
    private Set<Integer> mysqlPorts;

    @NotNull
    @JsonView(Full.class)
    private Set<Integer> postgresqlPorts;


    public DetectionConfig() {
        super(INSTANCE_ID);
    }

    public Set<Integer> getMongoPorts() {
        if (null == mongoPorts) {
            mongoPorts = new HashSet<>();
        }
        return mongoPorts;
    }

    public Set<Integer> getPostgresqlPorts() {
        if (null == postgresqlPorts) {
            postgresqlPorts = new HashSet<>();
        }
        return postgresqlPorts;
    }

    public Set<Integer> getMysqlPorts() {
        if (null == mysqlPorts) {
            mysqlPorts = new HashSet<>();
        }
        return mysqlPorts;
    }

    public static DetectionConfig defaultConfig() {
        DetectionConfig config = new DetectionConfig();
        config.getMongoPorts().add(27017);
        config.getMysqlPorts().add(3306);
        config.getPostgresqlPorts().add(5432);
        return config;
    }

}