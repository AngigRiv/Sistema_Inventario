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
import com.rios.sistemainventario.entity.Usuario;
import com.rios.sistemainventario.service.impl.CargoServiceImpl;
import com.rios.sistemainventario.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioServiceImpl service;
	@Autowired
	private CargoServiceImpl serviceCargo;
	
	@GetMapping()
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> usuarios=service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}
	
	 @GetMapping(value="/{id}") 
	public ResponseEntity<Usuario> getById(@PathVariable("id") int id){
		Usuario usuarios = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}
		
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuarios){
		Usuario usuarioDb=service.create(usuarios);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);
	}
		
	//localhost:8092/api/usuarios (PUT)
	@PutMapping
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuarios){
		Usuario usuarioDb=service.update(usuarios);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);
	}
		
	//localhost:8092/api/usuarios (DELETE)
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id) {
		return service.delete(id);
	}
		
	//localhost:8092/api/usuarios/selectCargos (get)
	@GetMapping(value="/selectCargos")
	public ResponseEntity<List<Cargo>> selectCargos(){
		List<Cargo> cargos=serviceCargo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(cargos);
	}	
}
