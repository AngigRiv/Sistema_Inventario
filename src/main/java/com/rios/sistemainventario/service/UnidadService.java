package com.rios.sistemainventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rios.sistemainventario.entity.Unidad;

@Service
public interface UnidadService {
	public List<Unidad> findAll();
	public Unidad findById(int id);
	public Unidad findByNombre(String nombre);
	public List<Unidad>findByNombreContaining(String nombre);
	public Unidad findByAbreviatura(String abreviatura);
	public List<Unidad>findByAbreviaturaContaining(String abreviatura);
	public Unidad create(Unidad obj);
	public Unidad update(Unidad obj);
	public int delete (int id);
}
