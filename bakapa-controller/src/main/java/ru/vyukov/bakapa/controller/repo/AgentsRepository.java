package ru.vyukov.bakapa.controller.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

import java.util.List;

@Repository
public interface AgentsRepository extends CrudRepository<Agent, String> {

	List<Agent> findAll();

}