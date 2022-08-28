package com.ecommerce.application.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.application.dtos.ProdutoGetDto;
import com.ecommerce.application.dtos.ProdutoPostDto;
import com.ecommerce.application.dtos.ProdutoPutDto;
import com.ecommerce.domain.interfaces.IProdutoDomainService;
import com.ecommerce.domain.models.Produto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produtos")
@RestController
@RequestMapping(value = "/v1/produtos")
public class ProdutosController {

	@Autowired
	private IProdutoDomainService produtoDomainService;

	private ModelMapper modelMapper;

	public ProdutosController() {
		this.modelMapper = new ModelMapper();
	}

	@ApiOperation("Serviço para cadastro de produto.")
	@PostMapping
	public ResponseEntity<ProdutoGetDto> post(@Valid @RequestBody ProdutoPostDto dto) {
		Produto produto = produtoDomainService.save(modelMapper.map(dto, Produto.class), dto.getCategoriaId());
		ProdutoGetDto response = modelMapper.map(produto, ProdutoGetDto.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@ApiOperation("Serviço para atualização de produto.")
	@PutMapping
	public ResponseEntity<ProdutoGetDto> put(@Valid @RequestBody ProdutoPutDto dto) {
		Produto produto = produtoDomainService.save(modelMapper.map(dto, Produto.class), dto.getCategoriaId());
		ProdutoGetDto response = modelMapper.map(produto, ProdutoGetDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation("Serviço para exclusão de produto.")
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoGetDto> delete(@PathVariable("id") Integer id) {
		Produto produto = produtoDomainService.delete(id);
		ProdutoGetDto response = modelMapper.map(produto, ProdutoGetDto.class);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@ApiOperation("Serviço para consulta de produtos por categoria.")
	@GetMapping("/{categoriaId}")
	public ResponseEntity<List<ProdutoGetDto>> getAll(@PathVariable("categoriaId") Integer categoriaId) {

		List<Produto> produtos = produtoDomainService.findAll(categoriaId);
		List<ProdutoGetDto> dto = modelMapper.map(produtos, new TypeToken<List<ProdutoGetDto>>() {
		}.getType());

		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
}
