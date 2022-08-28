package com.ecommerce.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.domain.interfaces.ICategoriaDomainService;
import com.ecommerce.domain.models.Categoria;
import com.ecommerce.infrastructure.repositories.ICategoriaRepository;

@Service
public class CategoriaDomainService implements ICategoriaDomainService{
	
	@Autowired
	private ICategoriaRepository repository;
	
	@Override
	public List<Categoria> findAll() {
		return repository.findAllByOrderByNomeAsc();
	}

	@Override
	public Categoria findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
	}

}
