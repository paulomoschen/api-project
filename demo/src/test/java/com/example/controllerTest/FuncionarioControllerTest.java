package com.example.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.controller.FuncionarioController;
import com.example.dao.FuncionarioRepository;
import com.example.model.Funcionario;

@SpringBootTest
public class FuncionarioControllerTest {
	
	@Autowired
	FuncionarioController funcionarioController;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Test
	public void testSalvarFuncionario() {
	    Funcionario funcionario = new Funcionario();
	    funcionario.setName("joe");
	    funcionario.setSobrenome("santos");
	    funcionario.setEmail("joe@email.com");

	    ResponseEntity<Funcionario> response = funcionarioController.salvarFuncionario(funcionario);
	    assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void testGetAllSemFuncionarios() {
	    ResponseEntity<List<Funcionario>> response = funcionarioController.getAll();
	    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	    assertTrue(response.getBody().isEmpty());
	}
	
	@Test
	public void testGetAllComFuncionarios() {
	    Funcionario funcionario1 = new Funcionario();
	    funcionario1.setName("joe");
	    funcionario1.setSobrenome("santos");
	    funcionario1.setEmail("joe@email.com");
	    
	    Funcionario funcionario2 = new Funcionario();
	    funcionario2.setName("Maria");
	    funcionario2.setSobrenome("silva");
	    funcionario2.setEmail("maria@email.com");
	    
	    funcionarioRepository.save(funcionario1);
	    funcionarioRepository.save(funcionario2);

	    ResponseEntity<List<Funcionario>> response = funcionarioController.getAll();
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(2, response.getBody().size());
	}
	
	@Test
	public void testErroInternoDoServidor() {
	    when(funcionarioRepository.save(any(Funcionario.class))).thenThrow(new RuntimeException("Erro ao salvar funcionário"));

	    Funcionario funcionario = new Funcionario();
	    funcionario.setName("joe");
	    funcionario.setSobrenome("santos");
	    funcionario.setEmail("joe@email.com");
	    ResponseEntity<Funcionario> response = funcionarioController.salvarFuncionario(funcionario);
	    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	    assertNull(response.getBody());
	}
}
