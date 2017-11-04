package ru.vyukov.bakapa.admin.controller.pages;

import ru.vyukov.bakapa.dto.config.DetectionConfigDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class DetectionConfigPage extends MainLayout {

    public DetectionConfigPage sendForm(DetectionConfigDTO config) {
        $("#mongoPorts").setValue(toJoinedString(config.getMongoPorts()));
        $("#mysqlPorts").setValue(toJoinedString(config.getMysqlPorts()));
        $("#postgresqlPorts").setValue(toJoinedString(config.getPostgresqlPorts()));

        $("#saveButton").click();

        return page(DetectionConfigPage.class);
    }

    private String toJoinedString(Set<Integer> ports) {
        if (null == ports) {
            return "";
        }
        List<String> strPorts = ports.stream().map((i) -> i.toString()).collect(Collectors.toList());
        return String.join(",", strPorts);
    }
}
