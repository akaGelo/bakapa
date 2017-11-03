package org.bakapa.dto.backups.database;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseLocationDTO {

    @NonNull
    @NotNull
    @NotEmpty
    private String host;

    @NonNull
    @NotNull
    @Min(0)
    private Integer port;


    @NonNull
    @NotNull
    @NotEmpty
    private String database;

    public static DatabaseLocationDTOBuilder databaseLocation() {
        //lombok static import builder not compiled in javac
        return builder();
    }


}
