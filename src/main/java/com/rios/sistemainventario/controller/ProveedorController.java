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

import com.rios.sistemainventario.entity.Proveedor;
import com.rios.sistemainventario.service.impl.ProveedorServiceImpl;



@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
	@Autowired
	private ProveedorServiceImpl service;
	
	@GetMapping()
	public ResponseEntity<List<Proveedor>> getAll(){
		List<Proveedor> proveedores = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(proveedores);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<Proveedor>getById(@PathVariable("id")int id){
		Proveedor proveedor=service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(proveedor);
	}
	@PostMapping
	public ResponseEntity<Proveedor> create(@RequestBody Proveedor proveedor){
		Proveedor proveedorDb=service.create(proveedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(proveedorDb);
		
	}
	@PutMapping
	public ResponseEntity<Proveedor> update(@RequestBody Proveedor proveedor){
		Proveedor proveedorDb=service.update(proveedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(proveedorDb);
		
	}
	@DeleteMapping(value="/{id}")
	public int delete (@PathVariable("id")int id) {
		return service.delete(id);
	}

}

