package ru.vyukov.bakapa.controller.controller.priv;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import ru.vyukov.bakapa.controller.controller.superbase.AbstractMockMcvControllerTest;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.Backup;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;
import ru.vyukov.bakapa.controller.service.backups.BackupsService;
import ru.vyukov.bakapa.controller.service.backupstargets.BackupsTargetsService;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BackupsPrivateApiController.class)
public class BackupsPrivateApiControllerTest extends AbstractMockMcvControllerTest {

    //test sorting
    //pagination tested in cloud contract

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<Pageable> pageableArgumentCaptor;

    @MockBean
    private BackupsService backupsService;

    @MockBean
    private AgentsService agentsService;

    @MockBean
    private BackupsTargetsService backupsTargetsService;


    @Test
    public void getBackupsByAgent() throws Exception {
        Agent agent = Agent.demo(TEST_AGENT_ID);
        when(agentsService.getAgent(TEST_AGENT_ID)).thenReturn(agent);

        ArrayList<Backup> backups = new ArrayList<>();
        when(backupsService.getBackups(eq(agent), any(Pageable.class))).thenReturn(new PageImpl<Backup>(backups));

        mockMvc.perform(get("/private/backups/").param("agent", TEST_AGENT_ID).param("sort", "state")).andExpect(status().isOk());
        verify(backupsService, only()).getBackups(eq(agent), any(Pageable.class));
    }

    @Test
    public void getBackupsByBackupTarget() throws Exception {
        AbstractBackupTarget backupTarget = AbstractBackupTarget.demo();
        final String backupTargetId = backupTarget.getBackupTargetId();
        when(backupsTargetsService.getBackupTarget(backupTargetId)).thenReturn(backupTarget);

        ArrayList<Backup> backups = new ArrayList<>();
        when(backupsService.getBackups(eq(backupTarget), any(Pageable.class))).thenReturn(new PageImpl<Backup>(backups));


        mockMvc.perform(get("/private/backups/").param("backupTarget", backupTargetId).param("sort", "state")).andExpect(status().isOk());
        verify(backupsService, only()).getBackups(eq(backupTarget), pageableArgumentCaptor.capture());

        Pageable pageable = pageableArgumentCaptor.getValue();
        Sort.Order orderFor = pageable.getSort().getOrderFor("state");
        assertEquals(ASC, orderFor.getDirection());
    }


}