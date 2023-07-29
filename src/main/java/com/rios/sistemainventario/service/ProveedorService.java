package com.rios.sistemainventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rios.sistemainventario.entity.Proveedor;



@Service
public interface ProveedorService {
	public List<Proveedor> findAll();
	public Proveedor findById(int id);
	public Proveedor findByNombre(String nombre);
	public List<Proveedor>findByNombreContaining(String nombre);
	public Proveedor findByRuc(String ruc);
	public List<Proveedor>findByRucContaining(String ruc);
	public Proveedor findByDireccion(String direccion);
	public List<Proveedor>findByDireccionContaining(String direccion);
	public Proveedor findByTelefono(Integer telefono);
	public List<Proveedor>findByTelefonoContaining(Integer telefono);
	public Proveedor findByCorreo(String correo);
	public List<Proveedor>findByCorreoContaining(String correo);
	public Proveedor create(Proveedor obj);
	public Proveedor update(Proveedor obj);
	public int delete (int id);
}
