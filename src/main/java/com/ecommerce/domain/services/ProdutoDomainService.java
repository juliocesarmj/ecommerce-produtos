package com.ecommerce.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.domain.interfaces.IProdutoDomainService;
import com.ecommerce.domain.models.Categoria;
import com.ecommerce.domain.models.Produto;
import com.ecommerce.infrastructure.repositories.ICategoriaRepository;
import com.ecommerce.infrastructure.repositories.IProdutoRepository;

@Service
public class ProdutoDomainService implements IProdutoDomainService {

	@Autowired
	private IProdutoRepository produtoRepository;

	@Autowired
	private ICategoriaRepository categoriaRepository;

	@Override
	public Produto save(Produto produto, Integer categoriaId) {

		Optional<Categoria> optional = validaCategoria(categoriaId);

		produto.setCategoria(optional.get());
		produtoRepository.save(produto);

		return produto;
	}

	private Optional<Categoria> validaCategoria(Integer categoriaId) {
		Optional<Categoria> optional = categoriaRepository.findById(categoriaId);
		if (optional.isEmpty())
			throw new IllegalArgumentException("Categoria inválida.");
		return optional;
	}

	@Override
	public Produto delete(Integer id) {

		Optional<Produto> optional = validaProduto(id);

		Produto produto = optional.get();
		produtoRepository.delete(produto);

		return produto;
	}

	private Optional<Produto> validaProduto(Integer id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isEmpty())
			throw new IllegalArgumentException("Produto inválido.");
		return optional;
	}

	@Override
	public List<Produto> findAll(Integer categoriaId) {
		return produtoRepository.findAllByCategoriaIdOrderByNomeAsc(categoriaId);
	}

}

