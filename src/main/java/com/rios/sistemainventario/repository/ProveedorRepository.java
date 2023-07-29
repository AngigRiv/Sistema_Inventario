package com.rios.sistemainventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rios.sistemainventario.entity.Proveedor;



@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>  {
	List<Proveedor> findByNombreContaining(String texto);
	Proveedor findByNombre(String texto);
	List<Proveedor> findByRucContaining(String texto);
	Proveedor findByRuc(String texto);
	List<Proveedor> findByDireccionContaining(String texto);
	Proveedor findByDireccion(String texto);
	List<Proveedor> findByTelefonoContaining(Integer texto);
	Proveedor findByTelefono(Integer texto);
	List<Proveedor> findByCorreoContaining(String texto);
	Proveedor findByCorreo(String texto);
	
}
