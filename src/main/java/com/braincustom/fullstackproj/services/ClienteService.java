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

import com.braincustom.fullstackproj.domain.Cliente;
import com.braincustom.fullstackproj.dto.ClienteDTO;
import com.braincustom.fullstackproj.repositories.ClienteRepository;
import com.braincustom.fullstackproj.services.exceptions.DataIntegrityException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository reposi;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = reposi.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null));
	}

	// método ATUALIZAÇÃO
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());// criando o Cliente no BD
		updateData(newObj, obj);// método auxiliar
		return reposi.save(newObj);
	}

	// método DELETE
	public void delete(Integer id) {
		find(id);
		try {
			reposi.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir, pois existe entidades relacionadas");
		}
	}

	// lista de TODAS as Clientes
	public List<Cliente> findAll() {
		return reposi.findAll();
	}

	// listagem por paginação que retorna as categorias
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return reposi.findAll(pageRequest);
	}

	// método auxiliar que instancia uma categoria apartir de um DTO
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	//criando o método auxiliar, será private pq não precisa ser exposto
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
