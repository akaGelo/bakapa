package ru.vyukov.bakapa.admin.controller.backups;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import ru.vyukov.bakapa.admin.controller.SuperUIMockTest;
import ru.vyukov.bakapa.admin.controller.TestEmptyPage;
import ru.vyukov.bakapa.admin.controller.pages.BackupsPage;
import ru.vyukov.bakapa.admin.service.agents.AgentsApiClient;
import ru.vyukov.bakapa.admin.service.agents.BackupsApiClient;
import ru.vyukov.bakapa.admin.service.agents.BackupsTargetsApiClient;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.vyukov.bakapa.admin.controller.TestEmptyPage.backupsEmptyPage;
import static ru.vyukov.bakapa.admin.controller.TestEmptyPage.backupsPage;


public class BackupsControllerMockTest extends SuperUIMockTest {

    @MockBean
    private AgentsApiClient agentsApiClient;

    @MockBean
    private BackupsApiClient backupsApiClient;

    @MockBean
    private BackupsTargetsApiClient targetsApiClient;

    @Captor
    private ArgumentCaptor<Pageable> pageable;

    @Captor
    private ArgumentCaptor<Filter> filter;


    @Test
    public void allBackupsPagination() throws Exception {
        when(backupsApiClient.getBackups(any(MultiValueMap.class))).thenReturn(
                backupsPage(1, 125), //test1
                backupsPage(0, 125), //test2
                backupsPage(0, 10)); //test3
        when(backupsApiClient.getBackups(any(), any())).thenCallRealMethod();


        //test1
        BackupsPage storageConfigPage = open("/backups/", BackupsPage.class);
        storageConfigPage.backupsTableShouldNotEmpty();

        storageConfigPage.currentPageIndicator().shouldHave(text("1"));

        //test2
        storageConfigPage.previousPageButton().click();
        storageConfigPage.currentPageIndicator().shouldHave(text("0"));
        storageConfigPage.previousPageButton().shouldHave(cssClass("disabled"));

        //test3
        storageConfigPage.nextPageButton().click();
        storageConfigPage.nextPageButton().shouldHave(cssClass("disabled"));
        storageConfigPage.currentPageIndicator().shouldHave(text("0"));
    }


    @Test
    public void allBackupsEmptyList() throws Exception {
        when(backupsApiClient.getBackups(any(MultiValueMap.class))).thenReturn(backupsEmptyPage());
        when(backupsApiClient.getBackups(any(), any())).thenCallRealMethod();

        BackupsPage storageConfigPage = open("/backups/?size=11&page=1&agent=agent1&target=target1", BackupsPage.class);
        storageConfigPage.backupsTableShouldEmpty();

        verify(backupsApiClient).getBackups(pageable.capture(), filter.capture());

        Pageable valPageable = pageable.getValue();
        Filter valFilter = filter.getValue();

        assertEquals(1, valPageable.getPageNumber());
        assertEquals(11, valPageable.getPageSize());

        assertEquals("agent1", valFilter.getAgent());
        assertEquals("target1", valFilter.getTarget());

    }


}