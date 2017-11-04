package ru.vyukov.bakapa.dto.config;

import cz.jirutka.validator.collection.constraints.EachMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

/**
 * Port info for find databases
 *
 * @author gelo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetectionConfigDTO {

    public static final String PATH_DETECTION_CONFIG = "/config/detection/";

    @NotNull
    @EachMin(1)
    private Set<Integer>  mongoPorts;

    @NotNull
    @EachMin(1)
    private Set<Integer> mysqlPorts;

    @NotNull
    @EachMin(1)
    private Set<Integer> postgresqlPorts;


    @Builder
    public DetectionConfigDTO(Integer mongoPort, Integer mysqlPort, Integer postgresqlPort) {
        this.mongoPorts = toSet(mongoPort);
        this.mysqlPorts = toSet(mysqlPort);
        this.postgresqlPorts = toSet(postgresqlPort);
    }


    private Set<Integer> toSet(Integer port) {
        if (null == port) {
            return null;
        }
        return Collections.singleton(port);
    }
}
