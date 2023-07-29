package com.rios.sistemainventario.service;

import java.util.List;

import com.rios.sistemainventario.entity.Marca;

public interface MarcaService {
	public List<Marca> findAll();
	public Marca findById(int id);
	public Marca findByNombre(String nombre);
	public List<Marca> findByNombreContaining(String nombre);
	public Marca create(Marca obj);
	public Marca update(Marca obj);
	public int delete(int id);
}
