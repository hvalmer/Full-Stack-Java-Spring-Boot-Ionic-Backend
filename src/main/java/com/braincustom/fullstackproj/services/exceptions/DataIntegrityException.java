package com.braincustom.fullstackproj.services.exceptions;

//implementação padrão de exceção
public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
