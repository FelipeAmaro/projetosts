package com.felipe.projetosts;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.felipe.projetosts.domain.Categoria;
import com.felipe.projetosts.domain.Cidade;
import com.felipe.projetosts.domain.Cliente;
import com.felipe.projetosts.domain.Endereco;
import com.felipe.projetosts.domain.Estado;
import com.felipe.projetosts.domain.Produto;
import com.felipe.projetosts.domain.enums.TipoCliente;
import com.felipe.projetosts.repositories.CategoriaRepository;
import com.felipe.projetosts.repositories.CidadeRepository;
import com.felipe.projetosts.repositories.ClienteRepository;
import com.felipe.projetosts.repositories.EnderecoRepository;
import com.felipe.projetosts.repositories.EstadoRepository;
import com.felipe.projetosts.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetostsApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired 
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetostsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*ADD Elementos no Banco*/
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador",2000.00);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse",80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Felipe Amaro", "felipe@gmail.com", "01988888807", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("55999999999","55999999997"));
		
		Endereco e1 = new Endereco(null, "Rua Alonso Medeiros", "180", "Casa", "Cohab Restinga", "97544020", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "150", "Apartamento", "Centro", "97544000", cli1, c2);
		
		/*Adiciona produtos as categorias*/
		
		/*Categoria add Produto*/
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		/*Produto add Categoria*/
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		/*Estado Add Cidade*/
		est1.getCidade().addAll(Arrays.asList(c1));
		est2.getCidade().addAll(Arrays.asList(c2, c3));
		
		/*Cliente add Endereco*/
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		/*Rapositorios*/
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
