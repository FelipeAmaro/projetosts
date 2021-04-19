package com.felipe.projetosts.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.projetosts.domain.Categoria;
import com.felipe.projetosts.repositories.CategoriaRepository;
import com.felipe.projetosts.services.exceptions.ObjectNotFoundExcepetion;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id){
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExcepetion(
				"Objeto não encontrado ! Id = " + id + ", Tipo: " + Categoria.class.getName()));
	}
}
