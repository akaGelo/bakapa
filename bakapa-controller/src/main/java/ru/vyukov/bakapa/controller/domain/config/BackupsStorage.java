package ru.vyukov.bakapa.controller.domain.config;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;
import ru.vyukov.bakapa.controller.domain.View.Full;

import javax.validation.constraints.NotNull;

/**
 * Storage (amazon s3 or minio.io)
 */
@Value
@EqualsAndHashCode(of = "id",callSuper = false)
public class BackupsStorage extends RuntimeConfig {


    public static final String INSTANCE_ID = "backupStorage";


    @NotNull
    @NonNull
    @NotEmpty
    @JsonView(Full.class)
    private String endpoint;

    @NotNull
    @NonNull
    @NotEmpty
    @JsonView(Full.class)
    private String bucket;


    @NotNull
    @NonNull
    @NotEmpty
    @JsonView(Full.class)
    private String accessKey;

    @NotNull
    @NonNull
    @NotEmpty
    private String secretKey;

    @Builder
    public BackupsStorage(String endpoint, String bucket, String accessKey, String secretKey) {
        super(INSTANCE_ID);
        this.endpoint = endpoint;
        this.bucket = bucket;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public static BackupsStorage defaultConfig() {
        return builder()
                .bucket("backups")
                .accessKey("myAccess")
                .secretKey("mySecret")
                .endpoint("http://sotrage.mycompany.ru").build();
    }


}

