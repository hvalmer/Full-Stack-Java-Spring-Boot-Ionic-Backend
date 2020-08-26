package com.braincustom.fullstackproj.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.braincustom.fullstackproj.domain.Cidade;
import com.braincustom.fullstackproj.domain.Cliente;
import com.braincustom.fullstackproj.domain.Endereco;
import com.braincustom.fullstackproj.domain.enums.TipoCliente;
import com.braincustom.fullstackproj.dto.ClienteDTO;
import com.braincustom.fullstackproj.dto.ClienteNewDTO;
import com.braincustom.fullstackproj.repositories.ClienteRepository;
import com.braincustom.fullstackproj.repositories.EnderecoRepository;
import com.braincustom.fullstackproj.services.exceptions.DataIntegrityException;

@Service
public class ClienteService {

	@Autowired //repositório de Cliente
	private ClienteRepository reposi;
	
	@Autowired //repositório do Endereço
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = reposi.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null));
	}
	
	@Transactional //garante salvar tanto os clientes como o endereço
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = reposi.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
			throw new DataIntegrityException("Não é possível excluir, pois há pedidos relacionados");
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
	
	//método para instanciar um cliente a partir do ClineteNewDTO
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente clie = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), clie, cid);
		clie.getEnderecos().add(end); //o cliente conheçe os endereços
		clie.getTelefones().add(objDto.getTelefone1());
		//testando se precisa adicionar os outros telefones
		if(objDto.getTelefone2()!=null) {
			clie.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			clie.getTelefones().add(objDto.getTelefone3());
		}
		return clie;
	}
	
	//criando o método auxiliar, será private pq não precisa ser exposto
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
