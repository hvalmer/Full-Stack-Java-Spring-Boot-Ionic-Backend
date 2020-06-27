package com.braincustom.fullstackproj.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braincustom.fullstackproj.domain.Pedido;
import com.braincustom.fullstackproj.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository reposi;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = reposi.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName(), null));
		}
}
