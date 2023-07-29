package com.rios.sistemainventario.service;
import java.util.List;

import com.rios.sistemainventario.entity.Administrador;

public interface AdministradorService {
	public List<Administrador> findAll();
	public Administrador findById(int id);
	public Administrador findByEmail(String email);
	public List<Administrador> findByEmailContaining(String email);
	public Administrador buscarPorEmail(String email);
	public boolean validarContraseña(String email, String password);
	public Administrador create(Administrador obj);
	public Administrador update(Administrador obj);
	public int delete(int id);

}
