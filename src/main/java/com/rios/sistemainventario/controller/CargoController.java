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

import com.rios.sistemainventario.entity.Cargo;
import com.rios.sistemainventario.service.impl.CargoServiceImpl;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {
	@Autowired
	private CargoServiceImpl service;
	
	//localhost:8092/api/cargos (GET)
	@GetMapping() 
	public ResponseEntity<List<Cargo>> getAll(){
		List<Cargo> cargos= service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(cargos);
	}
		
	//localhost:8092/api/cargos/1 (GET)
	@GetMapping(value="/{id}") 
	public ResponseEntity<Cargo> getById(@PathVariable("id") int id) {
		Cargo cargo = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(cargo);
	}
		
	//localhost:8092/api/cargos (POST)
	@PostMapping 
	public ResponseEntity<Cargo> create(@RequestBody Cargo cargo) {
		Cargo cargoDb=service.create(cargo);
		return ResponseEntity.status(HttpStatus.CREATED).body(cargoDb);
	}
		
	//localhost:8092/api/cargos/1 (PUT)
	@PutMapping
	public ResponseEntity<Cargo> update(@RequestBody Cargo cargo) {
		Cargo cargoDb=service.update(cargo);
		return ResponseEntity.status(HttpStatus.OK).body(cargoDb);
	}
		
	//localhost:8092/api/cargos/1 (DELETE)
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
