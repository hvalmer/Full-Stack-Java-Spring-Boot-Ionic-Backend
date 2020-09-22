package com.braincustom.fullstackproj;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.braincustom.fullstackproj.domain.Categoria;
import com.braincustom.fullstackproj.domain.Cidade;
import com.braincustom.fullstackproj.domain.Cliente;
import com.braincustom.fullstackproj.domain.Endereco;
import com.braincustom.fullstackproj.domain.Estado;
import com.braincustom.fullstackproj.domain.ItemPedido;
import com.braincustom.fullstackproj.domain.Pagamento;
import com.braincustom.fullstackproj.domain.PagamentoComBoleto;
import com.braincustom.fullstackproj.domain.PagamentoComCartao;
import com.braincustom.fullstackproj.domain.Pedido;
import com.braincustom.fullstackproj.domain.Produto;
import com.braincustom.fullstackproj.domain.enums.EstadoPagamento;
import com.braincustom.fullstackproj.domain.enums.TipoCliente;
import com.braincustom.fullstackproj.repositories.CategoriaRepository;
import com.braincustom.fullstackproj.repositories.CidadeRepository;
import com.braincustom.fullstackproj.repositories.ClienteRepository;
import com.braincustom.fullstackproj.repositories.EnderecoRepository;
import com.braincustom.fullstackproj.repositories.EstadoRepository;
import com.braincustom.fullstackproj.repositories.ItemPedidoRepository;
import com.braincustom.fullstackproj.repositories.PagamentoRepository;
import com.braincustom.fullstackproj.repositories.PedidoRepository;
import com.braincustom.fullstackproj.repositories.ProdutoRepository;

@SpringBootApplication
public class FullstackprojApplication implements CommandLineRunner {

	// dependência do objeto Categoria
	@Autowired
	private CategoriaRepository categoriaRepository;

	// dependência do objeto Produto
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//dependência do objeto Estado
	@Autowired
	private EstadoRepository estadoRepository;
	
	//dependência do objeto Cidade
	@Autowired
	private CidadeRepository cidadeRepository;

	//dependência do objeto Cliente
	@Autowired
	private ClienteRepository clienteRepository;
	
	//dependência do objeto Endereco
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	//dependência do objeto Pedido
	@Autowired
	private PedidoRepository pedidoRepository;
	
	//dependência do objeto Pagamento
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	//dependência do objeto ItemPedido
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FullstackprojApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// instanciando os objetos da Categoria e do Produto
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		// associando as categorias com os produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		// associando os produtos as suas categorias
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		// objeto responsável por salvar os dados no BD
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		// instanciando os objetos Estado e Cidade
		Estado est1 = new Estado(null, "Pará");
		Estado est2 = new Estado(null, "Goiás");

		Cidade c1 = new Cidade(null, "Belém", est1);
		Cidade c2 = new Cidade(null, "Castanhal", est1);
		Cidade c3 = new Cidade(null, "Goiânia", est2);
		Cidade c4 = new Cidade(null, "Anápolis", est2);

		// associando as cidades aos seus estados
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3, c4));
		
		//objeto responsável por salvar os dados no BD
		//salvar primeiro o Estado pq é um Estado para muitas Cidades
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		//instanciando os objetos Cliente
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@hotmail.com", "34554312345", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Santander S.A.", "santanderContato@santander.com", "90400888/0001-42", TipoCliente.PESSOAJURIDICA);
		
		//instanciando o telefone
		cli1.getTelefones().addAll(Arrays.asList("988782634", "32456778"));
		cli2.getTelefones().addAll(Arrays.asList("40041111", "40042324"));
		
		//instanciando o Endereco
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto. 1103", "Jardim", "66635-470", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "66650-475", cli1, c2);
		Endereco end3 = new Endereco(null, "Avenida República", "345", "Galeria Cedro", "Bueno", "74865-420", cli2, c3);
		
		//Cliente conhecendo os Enderecos
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecos().addAll(Arrays.asList(end3));
		
		//objeto responsável por salvar os dados no BD
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		//instanciando o Pedido
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2020 19:35"), cli2, end2);
				
		//instanciando o Pagamento
		//Para instanciar, utilizar o new com uma das subclasses
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2020 00:00"), null);
		ped2.setPagamento(pgto2);
		
		//associando o cli1 com os pedidos dele
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		//objeto responsável por salvar no BD
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		//instanciando o ItemPedido
		ItemPedido itp1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido itp2 = new ItemPedido(ped2, p3, 0.00, 2, 80.00);
		ItemPedido itp3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		//associando o ped1 e ped2
		ped1.getItens().addAll(Arrays.asList(itp1, itp2));
		ped2.getItens().addAll(Arrays.asList(itp3));
		
		//associando os produtos com itens de pedido
		p1.getItens().addAll(Arrays.asList(itp1));
		p2.getItens().addAll(Arrays.asList(itp3));
		p3.getItens().addAll(Arrays.asList(itp2));
		
		//objeto responsável por salvar no BD
		itemPedidoRepository.saveAll(Arrays.asList(itp1, itp2, itp3));
	}
}
