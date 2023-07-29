package com.rios.sistemainventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rios.sistemainventario.entity.Unidad;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Integer>  {
	List<Unidad> findByNombreContaining(String texto);
	Unidad findByNombre(String texto);
	List<Unidad> findByAbreviaturaContaining(String texto);
	Unidad findByAbreviatura(String texto);
}
