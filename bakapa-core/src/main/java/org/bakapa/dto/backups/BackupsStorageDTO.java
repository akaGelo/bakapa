package org.bakapa.dto.backups;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Storage (amazon s3 or minio.io)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackupsStorageDTO {

    @NotNull
    @NotEmpty
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

