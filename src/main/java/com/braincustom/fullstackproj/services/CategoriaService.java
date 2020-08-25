package com.braincustom.fullstackproj.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.braincustom.fullstackproj.domain.Categoria;
import com.braincustom.fullstackproj.dto.CategoriaDTO;
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
		Categoria newObj = find(obj.getId());// criando o Cliente no BD
		updateData(newObj, obj);// método auxiliar
		return reposi.save(newObj);
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
	
	//listagem por paginação que retorna as categorias 
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return reposi.findAll(pageRequest);
	}
	
	//método auxiliar que instancia uma categoria apartir de um DTO
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	//criando o método auxiliar, será private pq não precisa ser exposto
		private void updateData(Categoria newObj, Categoria obj) {
			newObj.setNome(obj.getNome());
	}
}
