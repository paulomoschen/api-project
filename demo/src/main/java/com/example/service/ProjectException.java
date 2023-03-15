package com.example.service;

public class ProjectException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String retorno;
	
	public ProjectException(String retorno) {
		super(retorno);
		this.retorno = retorno;
	}

	public String getRetorno() {
		return (retorno);
	}
}
