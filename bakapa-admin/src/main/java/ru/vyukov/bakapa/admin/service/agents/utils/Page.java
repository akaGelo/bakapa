package ru.vyukov.bakapa.admin.service.agents.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class Page<T> extends PageImpl<T> {


    @JsonCreator
    public Page(
            @JsonProperty("number") Integer number,
            @JsonProperty("totalElements") Integer totalElements,
            @JsonProperty("size") Integer size,
            @JsonProperty("content") List<T> content) {
        super(content, newPageable(number, size), totalElements);

    }

    private static Pageable newPageable(Integer number, Integer size) {
        return new PageRequest(number, size);
    }


    public boolean isEmpty(){
        return getContent().isEmpty();
    }

}
