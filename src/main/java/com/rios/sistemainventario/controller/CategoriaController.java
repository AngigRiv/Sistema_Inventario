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

import com.rios.sistemainventario.entity.Categoria;
import com.rios.sistemainventario.service.impl.CategoriaServiceImpl;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaServiceImpl service;
	
	//localhost:8092/api/categorias (GET)
	@GetMapping() 
	public ResponseEntity<List<Categoria>> getAll(){
		List<Categoria> categorias= service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(categorias);
	}
	
	//localhost:8092/api/categorias/1 (GET)
	@GetMapping(value="/{id}") 
	public ResponseEntity<Categoria> getById(@PathVariable("id") int id) {
		Categoria categoria = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(categoria);
	}
	
	//localhost:8092/api/categorias (POST)
	@PostMapping 
	public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
		Categoria categoriaDb=service.create(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDb);
	}
	
	//localhost:8092/api/categorias/1 (PUT)
	@PutMapping
	public ResponseEntity<Categoria> update(@RequestBody Categoria categoria) {
		Categoria categoriaDb=service.update(categoria);
		return ResponseEntity.status(HttpStatus.OK).body(categoriaDb);
	}
	
	//localhost:8092/api/categorias/1 (DELETE)
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
