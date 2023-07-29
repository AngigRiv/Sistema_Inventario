package com.rios.sistemainventario.service;

import java.util.List;

import com.rios.sistemainventario.entity.Cargo;
import com.rios.sistemainventario.entity.Usuario;

public interface UsuarioService {
	public List<Usuario> findAll();
	public Usuario findById(int id);
	public Usuario findByNombre(String nombre);
	public List<Usuario> findByNombreContaining(String nombre);
	public Usuario create(Usuario obj);
	public Usuario update(Usuario obj);
	public int delete(int id);
	public List<Cargo> selectCargo();
}
