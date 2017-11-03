package org.bakapa.dto.backups.database;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseUserCredentialsDTO {


    @NonNull
    @NotNull
    @NotEmpty
    private String password;

    @NonNull
    @NotNull
    @NotEmpty
    private String username;


    public static DatabaseUserCredentialsDTOBuilder userCredentials() {
        //lombok static import builder not compiled in javac
        return builder();
    }
}
