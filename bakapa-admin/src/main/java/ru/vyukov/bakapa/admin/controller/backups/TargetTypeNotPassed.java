package ru.vyukov.bakapa.admin.controller.backups;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class TargetTypeNotPassed extends Throwable {
}
