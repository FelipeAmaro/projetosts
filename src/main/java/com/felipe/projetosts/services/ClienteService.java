package com.felipe.projetosts.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.projetosts.domain.Cliente;
import com.felipe.projetosts.repositories.ClienteRepository;
import com.felipe.projetosts.services.exceptions.ObjectNotFoundExcepetion;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExcepetion(
				"Objeto não encontrado ! Id = " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
