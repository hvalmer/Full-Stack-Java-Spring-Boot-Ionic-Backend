package com.braincustom.fullstackproj.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.braincustom.fullstackproj.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	//operação para busca por email do Spring Data
	//método que busca no BD um Cliente passando o email como argumento
	//(readOnly=true)não necessita ser envolvida com BD, ficando mais rápida
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
