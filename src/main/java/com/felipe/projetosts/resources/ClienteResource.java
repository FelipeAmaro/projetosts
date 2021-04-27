package com.felipe.projetosts.resources;

/*
import java.util.ArrayList;
import java.util.List;
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.projetosts.domain.Cliente;
import com.felipe.projetosts.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")

public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value= "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
		/*
		Cliente cat1 = new Cliente(1, "Informática");
		Cliente cat2 = new Cliente(2, "Escritório");
		List<Cliente> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
		*/
	}

}
