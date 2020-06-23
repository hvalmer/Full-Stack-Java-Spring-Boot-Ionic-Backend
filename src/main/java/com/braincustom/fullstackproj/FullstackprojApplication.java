package com.braincustom.fullstackproj;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.braincustom.fullstackproj.domain.Categoria;
import com.braincustom.fullstackproj.domain.Produto;
import com.braincustom.fullstackproj.repositories.CategoriaRepository;
import com.braincustom.fullstackproj.repositories.ProdutoRepository;

@SpringBootApplication
public class FullstackprojApplication implements CommandLineRunner {

	//dependência do objeto Categoria
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//dependência do objeto Produto
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FullstackprojApplication.class, args);
	}

	//instanciando os objetos da Categoria e do Produto
	@Override
	public void run(String... args) throws Exception {
			
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//associando as categorias com os produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//associando os produtos as sua categoria
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		//objeto responsável por salvar as categorias no BD
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
