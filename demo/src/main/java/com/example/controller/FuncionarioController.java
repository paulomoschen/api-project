package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.dao.FuncionarioRepository;
import com.example.model.Funcionario;

@Component
public class FuncionarioController {
	@Autowired
	FuncionarioRepository funcionarioRepository;

	public ResponseEntity<Funcionario> salvarFuncionario(Funcionario funcionario) {
		try {			
			Funcionario _funcionario = funcionarioRepository.save(funcionario);
			return new ResponseEntity<>(_funcionario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Funcionario>> getAll() {
		try {
			List<Funcionario> Funcionarios = new ArrayList<Funcionario>();
			funcionarioRepository.findAll().forEach(Funcionarios::add);

			if (Funcionarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Funcionarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Funcionario> getById(String id) {
		Optional<Funcionario> funcionarioData = funcionarioRepository.findById(id);

		if (funcionarioData.isPresent()) {
			return new ResponseEntity<>(funcionarioData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Funcionario> updateFuncionario(String id, Funcionario funcionario) {
		Optional<Funcionario> funcionarioData = funcionarioRepository.findById(id);

		if (funcionarioData.isPresent()) {
			Funcionario _funcionario = funcionarioData.get();
			_funcionario.setName(funcionario.getName());
			_funcionario.setSobrenome(funcionario.getSobrenome());
			_funcionario.setEmail(funcionario.getEmail());
			_funcionario.setNis(funcionario.getNis());
			return new ResponseEntity<>(funcionarioRepository.save(_funcionario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<HttpStatus> deleteFuncionario(String id) {
		try {
			funcionarioRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
