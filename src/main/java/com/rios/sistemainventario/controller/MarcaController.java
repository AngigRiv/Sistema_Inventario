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

import com.rios.sistemainventario.entity.Marca;
import com.rios.sistemainventario.service.impl.MarcaServiceImpl;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {
	@Autowired
	private MarcaServiceImpl service;
	
	//localhost:8092/api/marcas (GET)
	@GetMapping() 
	public ResponseEntity<List<Marca>> getAll(){
		List<Marca> marcas= service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(marcas);
	}
		
	//localhost:8092/api/marcas/1 (GET)
	@GetMapping(value="/{id}") 
	public ResponseEntity<Marca> getById(@PathVariable("id") int id) {
		Marca marca = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(marca);
	}
		
	//localhost:8092/api/marcas (POST)
	@PostMapping 
	public ResponseEntity<Marca> create(@RequestBody Marca marca) {
		Marca marcaDb=service.create(marca);
		return ResponseEntity.status(HttpStatus.CREATED).body(marcaDb);
	}
		
	//localhost:8092/api/marcas/1 (PUT)
	@PutMapping
	public ResponseEntity<Marca> update(@RequestBody Marca marca) {
		Marca marcaDb=service.update(marca);
		return ResponseEntity.status(HttpStatus.OK).body(marcaDb);
	}
		
	//localhost:8092/api/marcas/1 (DELETE)
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
