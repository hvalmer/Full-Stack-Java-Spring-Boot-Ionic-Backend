package com.braincustom.fullstackproj;

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
import com.braincustom.fullstackproj.domain.Produto;
import com.braincustom.fullstackproj.domain.enums.TipoCliente;
import com.braincustom.fullstackproj.repositories.CategoriaRepository;
import com.braincustom.fullstackproj.repositories.CidadeRepository;
import com.braincustom.fullstackproj.repositories.ClienteRepository;
import com.braincustom.fullstackproj.repositories.EnderecoRepository;
import com.braincustom.fullstackproj.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(FullstackprojApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// instanciando os objetos da Categoria e do Produto
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		// associando as categorias com os produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		// associando os produtos as suas categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		// objeto responsável por salvar os dados no BD
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

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
		
		//instanciando o telefone
		cli1.getTelefones().addAll(Arrays.asList("988782634", "32456778"));
		
		//instanciando o Endereco
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto. 1103", "Jardim", "66635-470", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenita Matos", "105", "Sala 800", "Centro", "66650-475", cli1, c2);
		
		//Cliente conhecendo os Enderecos
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		//objeto responsável por salvar os dados no BD
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}
}
