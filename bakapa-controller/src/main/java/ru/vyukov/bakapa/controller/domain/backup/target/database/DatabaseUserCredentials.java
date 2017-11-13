package ru.vyukov.bakapa.controller.domain.backup.target.database;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class DatabaseUserCredentials {


    @NonNull
    @NotNull
    @NotEmpty
    @JsonView(Summary.class)
    private String password;

    @NonNull
    @NotNull
    @NotEmpty
    @JsonView(Summary.class)
    private String username;


    public static DatabaseUserCredentialsBuilder userCredentials(){
        //lombok static import builder not compiled in javac
        return builder();
    }
}
