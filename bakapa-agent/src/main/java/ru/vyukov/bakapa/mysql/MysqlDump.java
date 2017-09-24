package ru.vyukov.bakapa.mysql;

import java.io.BufferedInputStream;

public class MysqlDump {
	
	private MysqlDumpConfig config;


	public MysqlDump(MysqlDumpConfig config) {
		this.config = config;
	}
	

	
	public BufferedInputStream dump() {
		//тут делается дамп и читается вывод в std out
		return null;
	}
	

	public static String getVersion() {

		return null;
	}
}