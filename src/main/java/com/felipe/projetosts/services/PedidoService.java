package com.felipe.projetosts.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.projetosts.domain.Pedido;
import com.felipe.projetosts.repositories.PedidoRepository;
import com.felipe.projetosts.services.exceptions.ObjectNotFoundExcepetion;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id){
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExcepetion(
				"Objeto não encontrado ! Id = " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
