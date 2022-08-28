package com.ecommerce.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ecommerce.domain.models.Categoria;

@Component
public class LoadDatabase implements ApplicationRunner{
	
	@Autowired
	private ICategoriaRepository repository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repository.save(new Categoria(1, "Informática", null));
		repository.save(new Categoria(2, "Eletrônicos", null));
		repository.save(new Categoria(3, "Celulares", null));
		repository.save(new Categoria(4, "Games", null));
		repository.save(new Categoria(5, "Livraria", null));
		repository.save(new Categoria(6, "Outros", null));
	}
}
