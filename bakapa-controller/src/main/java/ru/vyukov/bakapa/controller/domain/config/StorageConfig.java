package ru.vyukov.bakapa.controller.domain.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import ru.vyukov.bakapa.controller.domain.View.Full;

import javax.validation.constraints.NotNull;

/**
 * Storage (amazon s3 or minio.io)
 */
@Value
@EqualsAndHashCode(of = "id", callSuper = false)
public class StorageConfig extends RuntimeConfig {


    public static final String INSTANCE_ID = "backupStorage";


    @NotNull
    @NonNull
    @NotEmpty
    @URL
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
    @JsonCreator
    public StorageConfig(@JsonProperty("endpoint") String endpoint,
                         @JsonProperty("bucket") String bucket,
                         @JsonProperty("accessKey") String accessKey,
                         @JsonProperty("secretKey") String secretKey) {
        super(INSTANCE_ID);
        this.endpoint = endpoint;
        this.bucket = bucket;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public static StorageConfig defaultConfig() {
        return builder()
                .bucket("backups")
                .accessKey("myAccess")
                .secretKey("mySecret")
                .endpoint("http://sotrage.mycompany.ru").build();
    }


}

