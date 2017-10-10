package ru.vyukov.bakapa.controller.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vyukov.bakapa.controller.domain.BackupsStorage;

import java.util.List;

@Repository
public interface BackupsStorageRepository extends CrudRepository<BackupsStorage, String> {

    List<BackupsStorage> findAll();
}
