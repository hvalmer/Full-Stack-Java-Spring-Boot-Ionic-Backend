package com.braincustom.fullstackproj.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.braincustom.fullstackproj.domain.Cliente;
import com.braincustom.fullstackproj.dto.ClienteDTO;
import com.braincustom.fullstackproj.dto.ClienteNewDTO;
import com.braincustom.fullstackproj.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//Atualizando a Cliente com PUT
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
			Cliente obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
		//Deletando na Cliente com DELETE
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		//Endpoint que retorna TODAS as Clientes
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll() {//retorna lista de ClienteDTO
			List<Cliente> list = service.findAll();//busco Cliente do BD
			List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());//convertendo DTO
			return ResponseEntity.ok().body(listDto);
		}
		
		//Endpoint para pegar uma requisição e chamar o método de serviço
		@RequestMapping(value="/page", method=RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> findPage(
				//para que sejam parâmetros opicionais
				@RequestParam(value="page", defaultValue="0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
				@RequestParam(value="direction", defaultValue="ASC") String direction) {
			Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction );
			Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
			return ResponseEntity.ok().body(listDto);
		}
}
