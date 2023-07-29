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

import com.rios.sistemainventario.entity.Unidad;
import com.rios.sistemainventario.service.impl.UnidadServiceImpl;

@RestController
@RequestMapping("/api/unidades")
public class UnidadController {
	@Autowired
	private UnidadServiceImpl service;
	
	@GetMapping()
	public ResponseEntity<List<Unidad>> getAll(){
		List<Unidad> unidades = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(unidades);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<Unidad>getById(@PathVariable("id")int id){
		Unidad unidad=service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(unidad);
	}
	@PostMapping
	public ResponseEntity<Unidad> create(@RequestBody Unidad unidad){
		Unidad unidadDb=service.create(unidad);
		return ResponseEntity.status(HttpStatus.CREATED).body(unidadDb);
		
	}
	@PutMapping
	public ResponseEntity<Unidad> update(@RequestBody Unidad unidad){
		Unidad unidadDb=service.update(unidad);
		return ResponseEntity.status(HttpStatus.CREATED).body(unidadDb);
		
	}
	@DeleteMapping(value="/{id}")
	public int delete (@PathVariable("id")int id) {
		return service.delete(id);
	}

}
