package com.braincustom.fullstackproj.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braincustom.fullstackproj.domain.Cliente;
import com.braincustom.fullstackproj.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository reposi;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = reposi.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null));
		}
}
