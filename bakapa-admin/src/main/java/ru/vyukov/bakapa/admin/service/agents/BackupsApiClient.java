package ru.vyukov.bakapa.admin.service.agents;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vyukov.bakapa.admin.controller.backups.Filter;
import ru.vyukov.bakapa.admin.service.agents.utils.Page;
import ru.vyukov.bakapa.dto.backups.BackupDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.StreamSupport.stream;

@FeignClient(value = "bakapa-controller")
@RequestMapping("/private/backups")
public interface BackupsApiClient {

    @GetMapping("/")
    Page<BackupDTO> getBackups(@RequestParam MultiValueMap<String, Object> params);

    default Page<BackupDTO> getBackups(Pageable pageable, Filter filter) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("page", pageable.getPageNumber());
        params.add("size", pageable.getPageSize());


        Sort sort = pageable.getSort();
        if (null != sort) {
            for (Sort.Order order : sort) {
                params.add("sort", order.getProperty() + "," + order.getDirection());
            }
        }

        if (filter.targetFilter()) {
            params.add("backupTarget", filter.getTarget());
        } else if (filter.agentFilter()) {
            params.add("agent", filter.getAgent());
        }

        return getBackups(params);
    }

}
