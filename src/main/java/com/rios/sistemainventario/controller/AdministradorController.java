package com.rios.sistemainventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rios.sistemainventario.entity.Administrador;
import com.rios.sistemainventario.service.impl.AdministradorServiceImpl;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {
	@Autowired
	private AdministradorServiceImpl service;
	
	//localhost:8091/api/Usuario (GET)
	@GetMapping() 
	public ResponseEntity<List<Administrador>> getAll(){
		List<Administrador> administrador= service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(administrador);
	}
	
	//localhost:8091/api/Usuario/1 (GET)
	@GetMapping(value="/{id}") 
	public ResponseEntity<Administrador> getById(@PathVariable("id") int id) {
		Administrador administrador = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(administrador);
	}
	
	//localhost:8091/api/Usuario (POST)
	@PostMapping 
	public ResponseEntity<Administrador> create(@RequestBody Administrador administrador) {
		Administrador administradorDb=service.create(administrador);
		return ResponseEntity.status(HttpStatus.CREATED).body(administradorDb);
	}
	
	//localhost:8091/api/Usuario/1 (PUT)
	@PutMapping
	public ResponseEntity<Administrador> update(@RequestBody Administrador administrador) {
		Administrador administradorDb=service.update(administrador);
		return ResponseEntity.status(HttpStatus.OK).body(administradorDb);
	}
	
	//localhost:8091/api/usuario/1 (DELETE)
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
