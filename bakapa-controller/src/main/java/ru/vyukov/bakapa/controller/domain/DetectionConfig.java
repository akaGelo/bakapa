package ru.vyukov.bakapa.controller.domain;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Document(collection = "detectionConfig")
public class DetectionConfig {

	public static final String INSTANCE_ID = "INSTANCE";

	@Id
	private final String id = INSTANCE_ID;

	@NotNull
	private Set<Integer> mongoPorts;

	@NotNull
	private Set<Integer> mysqlPorts;

	@NotNull
	private Set<Integer> postgresqlPorts;

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