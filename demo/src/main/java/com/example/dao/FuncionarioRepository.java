package com.example.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Funcionario;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {
	
	List<Funcionario> findByIdContaining(int id);

}
