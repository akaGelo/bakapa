package ru.vyukov.bakapa.admin;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "name")
public class HelloMessage {
    private String name;
}