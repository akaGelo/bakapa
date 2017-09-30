package ru.vyukov.bakapa.controller.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SuperController {

	@Autowired
	private ModelMapper modelMapper;

	protected <T, D> T convertDTO(D source, Class<T> destinationType) {
		T t = modelMapper.map(source, destinationType);
		return t;
	}

	protected <T, D> List<T> convertDTO(List<D> source, Class<T> destinationType) {
		return source.stream().map((s) -> modelMapper.map(s, destinationType)).collect(Collectors.toList());
	}
	
}
