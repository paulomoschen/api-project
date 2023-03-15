package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("funcionario")
public class Funcionario {
	 
	@Id
     private String id;

     private String name;
     private String sobrenome;
     private String email;
     private int nis;
     
     @Override
     public String toString() {
       return String.format(
           "Customer[id=%s, name='%s', sobrenome='%s', email='%s', nis='%d']",
           id, name, sobrenome, email, nis);
     }
     
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNis() {
		return nis;
	}

	public void setNis(int nis) {
		this.nis = nis;
	}
     
}
