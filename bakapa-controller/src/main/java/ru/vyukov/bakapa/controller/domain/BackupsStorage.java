package ru.vyukov.bakapa.controller.domain;

import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Storage (amazon s3 or minio.io)
 */
@Value
@Document(collection = "backupsStorage")
public class BackupsStorage {

    @Id
    private String storageId;

    @NotNull
    @NonNull
    @NotEmpty
    private String endpoint;

    @NotNull
    @NonNull
    @NotEmpty
    private String accessKey;

    @NotNull
    @NonNull
    @NotEmpty
    private String secretKey;
}

