package ru.vyukov.bakapa.domain;

public enum BackupTargetType {

    MYSQL,

    MONGODB,

    POSTGRESQL,

    FILESYSTEM;


    public boolean isDatabase() {
        return !isFilesystem();
    }

    public boolean isFilesystem() {
        return this == FILESYSTEM;
    }

    public static BackupTargetType[] filesystem() {
        return new BackupTargetType[]{FILESYSTEM};
    }

    public static BackupTargetType[] databases() {
        return new BackupTargetType[]{MYSQL, MONGODB, POSTGRESQL};
    }
}
