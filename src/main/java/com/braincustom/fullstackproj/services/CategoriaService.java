package com.braincustom.fullstackproj.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.braincustom.fullstackproj.domain.Categoria;
import com.braincustom.fullstackproj.repositories.CategoriaRepository;
import com.braincustom.fullstackproj.services.exceptions.DataIntegrityException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository reposi;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = reposi.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null));
		}
	
	//método INSERT
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return reposi.save(obj);
	}
	
	//método ATUALIZAÇÃO
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return reposi.save(obj);
	}
	
	//método DELETE
	public void delete(Integer id) {
		find(id);
		try {
			reposi.deleteById(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}
	
	//lista de TODAS as Categorias
	public List<Categoria> findAll(){
		return reposi.findAll();
	}
}
