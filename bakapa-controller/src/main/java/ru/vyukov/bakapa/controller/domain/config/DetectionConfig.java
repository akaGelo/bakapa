package ru.vyukov.bakapa.controller.domain.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import cz.jirutka.validator.collection.constraints.EachMin;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.vyukov.bakapa.controller.domain.View.Full;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
public class DetectionConfig extends RuntimeConfig {

    public static final String INSTANCE_ID = "detectionConfig";


    @NotNull
    @EachMin(1)
    @JsonView(Full.class)
    private Set<Integer> mongoPorts;

    @NotNull
    @EachMin(1)
    @JsonView(Full.class)
    private Set<Integer> mysqlPorts;

    @NotNull
    @EachMin(1)
    @JsonView(Full.class)
    private Set<Integer> postgresqlPorts;


    public DetectionConfig() {
        super(INSTANCE_ID);
    }

    @JsonCreator
    @Builder
    public DetectionConfig(@JsonProperty("mongoPorts") Set<Integer> mongoPorts,
                           @JsonProperty("mysqlPorts") Set<Integer> mysqlPorts,
                           @JsonProperty("postgresqlPorts") Set<Integer> postgresqlPorts) {
        super(INSTANCE_ID);
        this.mongoPorts = mongoPorts;
        this.mysqlPorts = mysqlPorts;
        this.postgresqlPorts = postgresqlPorts;
    }


    public static DetectionConfig defaultConfig() {
        return builder().mongoPorts(setOf(27017)).mysqlPorts(setOf(3306)).postgresqlPorts(setOf(5432)).build();
    }

    private static Set setOf(Integer port) {
        //@Singular not working in @Builder constructor
        Set set = new HashSet();
        set.add(port);
        return set;
    }

}