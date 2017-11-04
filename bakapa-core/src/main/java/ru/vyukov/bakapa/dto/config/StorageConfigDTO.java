package ru.vyukov.bakapa.dto.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

/**
 * Storage (amazon s3 or minio.io)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageConfigDTO {

    @NotNull
    @NotEmpty
    @URL
    private String endpoint;

    @NotNull
    @NotEmpty
    private String bucket;

    @NotNull
    @NotEmpty
    private String accessKey;

    @NotNull
    @NotEmpty
    private String secretKey;
}

