package ru.vyukov.bakapa.controller.domain;

public interface View {

    public static interface Summary {
    }


    public static interface Full extends Summary {
    }

    /**
     * View for new Agent
     */
    interface Credentials extends Full {
    }


}
