package ru.vyukov.bakapa.controller.domain.backup;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;
import ru.vyukov.bakapa.controller.domain.View.Summary;

import javax.validation.constraints.NotNull;

/**
 * Target directory
 */
@Getter
@AllArgsConstructor
public class DirectoryBackupTarget extends AbstractBackupTarget {


    @NonNull
    @NotNull
    @NotEmpty
    @JsonView(Summary.class)
    private String path;


}
