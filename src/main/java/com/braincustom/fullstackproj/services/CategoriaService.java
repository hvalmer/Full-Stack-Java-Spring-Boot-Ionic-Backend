package com.braincustom.fullstackproj.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braincustom.fullstackproj.domain.Categoria;
import com.braincustom.fullstackproj.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository reposi;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = reposi.findById(id);
		return obj.orElse(null);
	}
}
