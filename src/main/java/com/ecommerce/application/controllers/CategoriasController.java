package com.ecommerce.application.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.application.dtos.CategoriaGetDto;
import com.ecommerce.domain.interfaces.ICategoriaDomainService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/categorias")
@Api(tags = "Categorias")
public class CategoriasController {
	
	@Autowired
	ICategoriaDomainService service;
	
	private ModelMapper mapper;
	
	public CategoriasController() {
		this.mapper = new ModelMapper();
	}
	
	@GetMapping
	@ApiOperation("Serviço para consulta de categorias")
	public ResponseEntity<List<CategoriaGetDto>> getAll() {
		return ResponseEntity.ok(mapper.map(service.findAll(), new TypeToken<List<CategoriaGetDto>>() {}.getType()));
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Serviço para consulta de categorias")
	public ResponseEntity<CategoriaGetDto> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(mapper.map(this.service.findById(id), CategoriaGetDto.class));
	}
}
