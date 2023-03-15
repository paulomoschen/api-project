package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.FuncionarioController;
import com.example.dao.FuncionarioRepository;
import com.example.model.Funcionario;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class FuncionarioService {
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Autowired
	FuncionarioController funcionarioController;

	@PostMapping("/funcionarios")
	public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioController.salvarFuncionario(funcionario);
	}

	@GetMapping("/funcionarios")
	public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
		return funcionarioController.getAll();
	}

	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> updateFuncionario(@PathVariable("id") String id,
			@RequestBody Funcionario funcionario) {
		return funcionarioController.updateFuncionario(id, funcionario);
	}

	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity<HttpStatus> deleteFuncionario(@PathVariable("id") String id) {
		return funcionarioController.deleteFuncionario(id);
	}

}
