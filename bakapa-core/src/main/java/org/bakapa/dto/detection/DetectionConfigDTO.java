package org.bakapa.dto.detection;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Port info for find databases
 * 
 * @author gelo
 *
 */
@Data
public class DetectionConfigDTO {

	public static final String PATH_DETECTION_CONFIG = "/contracts.controller.controller.contracts.controller.controller.priv.config/config/";

	@NotNull
	private Set<Integer> mongoPorts;

	@NotNull
	private Set<Integer> mysqlPorts;

	@NotNull
	private Set<Integer> postgresqlPorts;

}
