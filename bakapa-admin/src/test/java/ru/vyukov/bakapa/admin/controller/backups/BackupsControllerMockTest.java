package ru.vyukov.bakapa.admin.controller.backups;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;
import ru.vyukov.bakapa.admin.service.agents.BackupsApiClient;
import ru.vyukov.bakapa.admin.service.agents.BackupsTargetsApiClient;

import static org.junit.Assert.*;


@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BackupsController.class)
public class BackupsControllerMockTest {

    @MockBean
    private AgentsApiClient agentsApiClient;

    @MockBean
    private BackupsApiClient backupsApiClient;

    @MockBean
    private BackupsTargetsApiClient targetsApiClient;

    @Test
    public void allBackups() throws Exception {
        fail("not implemented");
    }

    @Test
    public void allBackupsFilter() throws Exception {
        fail("not implemented");
    }


    @Test
    public void allBackupsPagination() throws Exception {
        fail("not implemented");
    }


    @Test
    public void allBackupsEmptyList() throws Exception {
        fail("not implemented");
    }


}