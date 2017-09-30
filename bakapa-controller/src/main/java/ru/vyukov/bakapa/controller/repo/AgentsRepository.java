package ru.vyukov.bakapa.controller.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.vyukov.bakapa.controller.domain.Agent;

@Repository
public interface AgentsRepository extends CrudRepository<Agent, String> {

	List<Agent> findAll();

}