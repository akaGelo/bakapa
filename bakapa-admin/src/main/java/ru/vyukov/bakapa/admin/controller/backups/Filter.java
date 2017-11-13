package ru.vyukov.bakapa.admin.controller.backups;

import lombok.Data;

@Data
public class Filter {

    private String agent;

    private String target;

    public boolean targetFilter() {
        return null != target;
    }


    public boolean agentFilter() {
        return null != agent;
    }
}
