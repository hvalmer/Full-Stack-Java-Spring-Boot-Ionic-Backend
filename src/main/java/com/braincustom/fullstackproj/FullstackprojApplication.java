package com.braincustom.fullstackproj;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.braincustom.fullstackproj.domain.Categoria;
import com.braincustom.fullstackproj.repositories.CategoriaRepository;

@SpringBootApplication
public class FullstackprojApplication implements CommandLineRunner {

	//dependência do objeto Categoria
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FullstackprojApplication.class, args);
	}

	//instanciando os objetos da Categoria
	@Override
	public void run(String... args) throws Exception {
			
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		//objeto responsável por salvar as categorias no BD
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	}

}
