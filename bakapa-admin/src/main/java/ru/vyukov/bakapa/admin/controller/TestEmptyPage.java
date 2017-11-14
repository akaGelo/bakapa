package ru.vyukov.bakapa.admin.controller;


import lombok.Builder;
import ru.vyukov.bakapa.dto.backups.BackupDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestEmptyPage<T> extends ru.vyukov.bakapa.admin.service.agents.utils.Page<T> {

    private TestEmptyPage() {
        super(0, 0, 20, Collections.emptyList());
    }

    @Builder
    private TestEmptyPage(Integer number, Integer totalElements, Integer size, List<T> content) {
        super(number, totalElements, size, content);
    }

    public static <T> TestEmptyPage<T> backupsEmptyPage() {
        return new TestEmptyPage<T>();
    }


    public static TestEmptyPage<BackupDTO> backupsPage(int page, int totalSize) {
        return new TestEmptyPage<BackupDTO>(page, totalSize, 20, demoContent(20));
    }


    private static List<BackupDTO> demoContent(int size) {
        return IntStream.range(0, size).mapToObj(BackupDTO::demo).collect(Collectors.toList());
    }
}
