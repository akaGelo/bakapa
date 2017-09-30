package ru.vyukov.bakapa.controller.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.vyukov.bakapa.controller.domain.DetectionConfig;

@Repository
public interface DetectionConfigRepository extends CrudRepository<DetectionConfig, String> {

	
	
}