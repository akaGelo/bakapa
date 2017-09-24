package ru.vyukov.bakapa.mysql;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class MysqlDumpConfig {

	@NonNull
	private String host;
	
	@NonNull
	private Integer port;

	
	@NonNull
	private List<String> databases;
	
	@NonNull
	private List<String> tables;
	
	@NonNull
	private List<String> excludeTables;
}
