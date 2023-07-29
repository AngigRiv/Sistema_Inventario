package com.rios.sistemainventario.service;

import java.util.List;

import com.rios.sistemainventario.entity.Cargo;

public interface CargoService {
	public List<Cargo> findAll();
	public Cargo findById(int id);
	public Cargo findByNombre(String nombre);
	public List<Cargo> findByNombreContaining(String nombre);
	public Cargo create(Cargo obj);
	public Cargo update(Cargo obj);
	public int delete(int id);
}
