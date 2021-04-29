package com.felipe.projetosts;

import java.text.SimpleDateFormat;
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
import com.felipe.projetosts.domain.ItemPedido;
import com.felipe.projetosts.domain.Pagamento;
import com.felipe.projetosts.domain.PagamentoComBoleto;
import com.felipe.projetosts.domain.PagamentoComCartao;
import com.felipe.projetosts.domain.Pedido;
import com.felipe.projetosts.domain.Produto;
import com.felipe.projetosts.domain.enums.EstadoPagamento;
import com.felipe.projetosts.domain.enums.TipoCliente;
import com.felipe.projetosts.repositories.CategoriaRepository;
import com.felipe.projetosts.repositories.CidadeRepository;
import com.felipe.projetosts.repositories.ClienteRepository;
import com.felipe.projetosts.repositories.EnderecoRepository;
import com.felipe.projetosts.repositories.EstadoRepository;
import com.felipe.projetosts.repositories.ItemPedidoRepository;
import com.felipe.projetosts.repositories.PagamentoRepository;
import com.felipe.projetosts.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	

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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
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
		
		/*Cliente add Pedido*/
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		/*Pedido add Itens*/
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		/*Produto add Itens*/
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		/*Rapositorios*/
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
