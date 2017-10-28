package ru.vyukov.bakapa.controller.domain.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Stored in repository configuration object
 */
@Document(collection = "runtimeConfig")
@EqualsAndHashCode(of = "id")
public abstract class RuntimeConfig {


    @Id
    @Getter
    private String id;

    protected RuntimeConfig(String id) {
        this.id = id;
    }



}
