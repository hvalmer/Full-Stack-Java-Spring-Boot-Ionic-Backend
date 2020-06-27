package com.braincustom.fullstackproj.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	// método construtor -- private pq é enum
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	//apenas o get é implementado no enum
	public int getCod() {
		return cod;
	}
		
	public String getDescricao() {
		return descricao;
	}
		
	//método static rodado mesmo não tendo objeto instanciado
	public static EstadoPagamento toEnum(Integer cod) {
			
		if(cod == null) {
			return null;
		}
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
			
		//Exceção
		throw new IllegalArgumentException("Id inválido: " + cod);
		}
}
