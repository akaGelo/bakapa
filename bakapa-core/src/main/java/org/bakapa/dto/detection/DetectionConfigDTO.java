package org.bakapa.dto.detection;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Port info for find databases
 * 
 * @author gelo
 *
 */
@Data
public class DetectionConfigDTO {

	public static final String PATH_DETECTION_CONFIG = "/contracts.controller.controller.contracts.controller.controller.priv.detection/config/";

	@NotNull
	private Set<Integer> mongoPorts;

	@NotNull
	private Set<Integer> mysqlPorts;

	@NotNull
	private Set<Integer> postgresqlPorts;

}
