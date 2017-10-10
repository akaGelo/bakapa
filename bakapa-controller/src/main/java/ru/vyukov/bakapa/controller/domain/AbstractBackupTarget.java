package ru.vyukov.bakapa.controller.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.bakapa.domain.BackupTargetType;

import javax.validation.constraints.NotNull;


/**
 * Abstract backup target
 */
@AllArgsConstructor
abstract public class AbstractBackupTarget {

    @NonNull
    @NotNull
    @Getter
    private BackupTargetType targetType;


}