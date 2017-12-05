package ru.vyukov.bakapa.admin.service.agents.backups;

import org.mockito.junit.MockitoJUnitRunner;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.agent.AgentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;


@RunWith(MockitoJUnitRunner.class)
public class BackupTargetsFactoryTest {

    @InjectMocks
    private BackupTargetsFactoryImpl underTest;

    @Test
    public void newInstance() throws Exception {
        AgentDTO agent = AgentDTO.demo("test");

        for (BackupTargetType type : BackupTargetType.values()) {
            underTest.newInstance(agent, type);
        }
    }

}