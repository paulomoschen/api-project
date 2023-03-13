package com.example.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.Funcionario;

public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {
	
	List<Funcionario> findByNomeContaining(String nome);

}
